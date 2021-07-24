package com.waffle.pancake.integrated.tech.thread.vl;

import java.util.concurrent.TimeUnit;

/**
 * @author yixiaoshuang
 * @date 2020/12/20 14:45
 */
public class VolatileTest2 {

    /**
     * volatile关键字能保证变量在内存中的可见性。
     */
    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (running) {
            }

            System.out.println("end");
        }, "server").start();


        TimeUnit.SECONDS.sleep(3);

        running = false;

    }

}
