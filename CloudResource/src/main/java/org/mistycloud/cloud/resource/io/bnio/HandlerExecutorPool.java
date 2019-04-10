package org.mistycloud.cloud.resource.io.bnio;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xieyuejun
 * @created 2019/2/16 14:46
 */
public class HandlerExecutorPool implements Executor {

    private ExecutorService executor;

    public HandlerExecutorPool(int maxPoolSize, int queueSize) {
        int processors = Runtime.getRuntime().availableProcessors();
        this.executor = new ThreadPoolExecutor(maxPoolSize > processors ? processors : maxPoolSize
                , maxPoolSize, 2L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize), new ThreadFactory() {
            private String namePrefix = "Handler-";
            private final AtomicInteger threadNumber = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
                if (t.isDaemon())
                    t.setDaemon(false);
                if (t.getPriority() != Thread.NORM_PRIORITY)
                    t.setPriority(Thread.NORM_PRIORITY);
                return t;
            }
        }, new ThreadPoolExecutor.AbortPolicy());
    }

    @Override
    public void execute(Runnable command) {
        this.executor.execute(command);
    }
}
