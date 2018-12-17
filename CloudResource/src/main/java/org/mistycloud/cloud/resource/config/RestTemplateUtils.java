package org.mistycloud.cloud.resource.config;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class RestTemplateUtils {
//    static {
//        disableSslVerification();
//    }
//
//
//    public static RestTemplate getDefaultRestTemplate() {
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        //关闭此缓存,可以支持大文件传输
//        factory.setBufferRequestBody(false);
//        factory.setConnectTimeout(30000);
//        RestTemplate rt = new RestTemplateBuilder().requestFactory(factory).build();
//        return rt;
//    }

//    public static RestTemplate getAcceptsUntrustedCertsRestTemplate() {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(acceptsUntrustedCertsHttpClient());
//        RestTemplate rt = new RestTemplate(factory);
//        return rt;
//    }

    /**
     * 构建不校验证书的HttpClient
     *
     * @return
     * @throws Exception
     */
//    private static CloseableHttpClient acceptsUntrustedCertsHttpClient() {
//        HttpClientBuilder builder = HttpClientBuilder.create();
//        SSLContext sslContext = null;
//        try {
//            sslContext = new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (arg0, arg1) -> true).build();
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        } catch (KeyManagementException e) {
//            throw new RuntimeException(e);
//        } catch (KeyStoreException e) {
//            throw new RuntimeException(e);
//        }
//        builder.setSSLContext(sslContext);
//
//        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
//
//        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory).build();
//
//        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//        connMgr.setMaxTotal(200);
//        connMgr.setDefaultMaxPerRoute(100);
//        builder.setConnectionManager(connMgr);
//
//        CloseableHttpClient client = builder.build();
//
//        return client;
//    }


    //https忽略证书
    private static void disableSslVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
