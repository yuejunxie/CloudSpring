package org.mistycloud.cloud.usercenter.lock.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author xieyuejun
 * @created 2019/1/21 11:56
 */
@Slf4j
public class ZookeeperLock implements Lock, Watcher {

    private ZooKeeper zooKeeper;

    private String ROOT_LOCK = "/locks";

    private String lockName;

    private String WAIT_LOCK;

    private String CURRENT_LOCK;

    // 计数器
    private CountDownLatch countDownLatch;
    private int sessionTimeout = 3 * 60 * 1000;
    private List<Exception> exceptionList = new ArrayList<>();

    public ZookeeperLock(String config, String lockName) {
        this.lockName = lockName;
        try {
            zooKeeper = new ZooKeeper(config, sessionTimeout, this);
            Stat stat = zooKeeper.exists(ROOT_LOCK, false);
            if (stat == null) {
                zooKeeper.create(ROOT_LOCK, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            log.error("", e);
        } catch (InterruptedException e) {
            log.error("", e);
        } catch (KeeperException e) {
            log.error("", e);
        }
    }


    @Override
    public void process(WatchedEvent event) {
        if (this.countDownLatch != null) {
            this.countDownLatch.countDown();
        }
    }

    @Override
    public void lock() {
        if (exceptionList.size() > 0) {
            throw new RuntimeException();
        }
        try {
            if (this.tryLock()) {
                log.info(Thread.currentThread().getName() + " " + lockName + "获得了锁");
                return;
            } else {
                // 等待锁
                waitForLock(WAIT_LOCK, sessionTimeout);
            }
        } catch (InterruptedException e) {
            log.error("", e);
        } catch (KeeperException e) {
            log.error("", e);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        lock();
    }

    @Override
    public boolean tryLock() {
        try {
            String splitStr = "_lock_";
            if (lockName.contains(splitStr)) {
                throw new RuntimeException("锁名有误");
            }
            CURRENT_LOCK = zooKeeper.create(ROOT_LOCK + "/" + lockName + splitStr, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            log.info(CURRENT_LOCK + "已创建");
            List<String> subNodes = zooKeeper.getChildren(ROOT_LOCK, false);
            // 取出所有lockName的锁
            List<String> lockObjects = new ArrayList<String>();
            for (String subNode : subNodes) {
                String _node = subNode.split(splitStr)[0];
                if (_node.equals(lockName)) {
                    lockObjects.add(subNode);
                }
            }
            Collections.sort(lockObjects);
            log.info(Thread.currentThread().getName() + "锁是" + CURRENT_LOCK);
            //若当前节点为最小节点，则获取锁成功
            if (CURRENT_LOCK.equals(ROOT_LOCK + "/" + lockObjects.get(0))) {
                return true;
            }
            String prevNode = CURRENT_LOCK.substring(CURRENT_LOCK.lastIndexOf("/") + 1);
            WAIT_LOCK = lockObjects.get(Collections.binarySearch(lockObjects, prevNode) - 1);
        } catch (InterruptedException e) {
            log.error("", e);
        } catch (KeeperException e) {
            log.error("", e);
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        try {
            if (this.tryLock()) {
                return true;
            }
            return waitForLock(WAIT_LOCK, time);
        } catch (Exception e) {
            log.error("", e);
        }
        return false;
    }

    private boolean waitForLock(String prev, long time) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(ROOT_LOCK + "/" + prev, true);
        if (stat != null) {
            log.info(Thread.currentThread().getName() + "等待锁 " + ROOT_LOCK + "/" + prev);
            this.countDownLatch = new CountDownLatch(1);
            this.countDownLatch.await();

        }
        return true;
    }

    @Override
    public void unlock() {
        try {
            log.info("释放锁" + CURRENT_LOCK);
            zooKeeper.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            log.error("", e);
        } catch (KeeperException e) {
            log.error("", e);
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
