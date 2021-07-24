package com.waffle.pancake.integrated.tech.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * 测试semaphore信号量
 *
 * @author yixiaoshuang
 * @date 2020/11/30 21:37
 */
public class UsualSemaphoreSample {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SpWorker(semaphore));
            thread.start();
        }
    }

}

@Slf4j
class SpWorker implements Runnable {

    private String name;
    private Semaphore semaphore;

    public SpWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            log("is waiting for a permit!");
            semaphore.acquire();
            log("acquired a msg");
        } catch (InterruptedException e) {

        } finally {
            log("released a permit!");
            semaphore.release();
        }

    }

    private void log(String msg) {
        if (name == null) {
            name = Thread.currentThread().getName();
        }
        System.out.println(name + " " + msg);
//        log.info("name:{},msg:{}", name, msg);
    }
}
