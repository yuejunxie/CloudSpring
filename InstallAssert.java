import com.sun.deploy.util.StringUtils;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class InstallAssert {

    private char[] passphrase = "changgeit".toCharArray();
    private String host;
    private int port;
    private File jreSecurity;

    public InstallAssert(String host) {
        this(host, 443);
    }


    public InstallAssert(String host, int i) {
        this.host = host;
        if (StringUtils.isEmpty(host)) {
            throw new IllegalArgumentException();
        }
        if (port < 1)
            port = 443;
        this.port = port;
        jreSecurity = new File(System.getProperty("java.home"), "lib/security");
        if (!jreSecurity.exists()) {
            System.out.println();
        }
    }

    public void install() throws GeneralSecurityException, IOException {
        File storeFile = getStoreFile();
        KeyStore ks = loadKeyStore(storeFile);
        SSLContext context = SSLContext.getInstance("TLS");
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[]{ tm }, null);

        SSLSocketFactory factory = context.getSocketFactory();

        SSLSocket socket = (SSLSocket) factory.createSocket(host,port);
        try {
            socket.setSoTimeout(10000);
            socket.startHandshake();
            return;
        }catch (SSLException e){

        }finally {
            closeQuitely(socket);
        }

        X509Certificate[] chain = tm.chain;
        if (chain==null||chain.length==0){
            throw  new GeneralSecurityException("");
        }

        X509Certificate cert = chain[0];
        String alias  = host+"-"+port;
        ks.setCertificateEntry(alias,cert);
        OutputStream out = new FileOutputStream(storeFile);
        try{
            ks.store(out,passphrase);
        }finally {
            closeQuitely(out);

        }

    }


}
