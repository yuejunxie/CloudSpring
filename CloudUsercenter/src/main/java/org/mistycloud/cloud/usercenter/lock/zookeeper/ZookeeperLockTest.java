package org.mistycloud.cloud.usercenter.lock.zookeeper;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Author: JackShieh
 * Corporation: Drawinds LTD
 * WE FLY WE DREAM
 * cloud-spring
 * Created: 2019/1/22 21:40
 * Description:
 */
@Slf4j
public class ZookeeperLockTest {
    static int n = 500;

    public static void secskill() {
        log.info("" + --n);
    }

    public static void main(String[] args) throws InterruptedException {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        CountDownLatch countDownLatch = new CountDownLatch(200);
        Runnable runnable = () -> {
            ZookeeperLock lock = null;
            try {
                lock = new ZookeeperLock("127.0.0.1:2181", "test1");
                lock.lock();
                map.put(1, map.get(1) + 1);
                secskill();
                log.info(Thread.currentThread().getName() + "正在运行");
            } finally {
                if (lock != null) {
                    lock.unlock();
                }
                countDownLatch.countDown();
            }
        };


        for (int i = 0; i < 200; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
        countDownLatch.await();
        log.info("" + map.get(1));

    }

}
