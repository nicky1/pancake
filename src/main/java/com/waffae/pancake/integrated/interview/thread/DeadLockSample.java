package com.waffae.pancake.integrated.interview.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 死锁的测试-循环依赖
 *
 * @author yixiaoshuang
 * @date 2020/11/30 17:53
 */
@Slf4j
public class DeadLockSample {

    public static void main(String[] args) throws Exception {
        String lockA = "lockA";
        String lockB = "lockB";

        DeadData data = new DeadData("t1", lockA, lockB);

        DeadData data1 = new DeadData("t2", lockB, lockA);

        Thread t1 = new Thread(data::monitorDead);

        Thread t2 = new Thread(data1::monitorDead);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info("done");
    }
}

@Slf4j
class DeadData {
    private String first;
    private String second;
    private String name;

    public DeadData() {
    }

    public DeadData(String name, String first, String second) {
        this.first = first;
        this.second = second;
        this.name = name;
    }

    public void monitorDead() {
        synchronized (first) {
            log.info("name1 :{},obtained:{}", name, first);

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }

            synchronized (second) {
                log.info("name2 :{},obtained:{}", name, second);
            }

        }
    }
}
