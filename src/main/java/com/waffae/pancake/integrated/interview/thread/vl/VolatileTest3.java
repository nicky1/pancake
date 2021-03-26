package com.waffae.pancake.integrated.interview.thread.vl;

/**
 * 阿里面试题-一个任务由N个子任务构成,每个子任务的时长不同,如果其中一个任务失败,则所有任务结束。
 *
 * @author yixiaoshuang
 * @date 2020/12/20 14:45
 */
public class VolatileTest3 {

    /**
     * volatile关键字能保证变量在内存中的可见性。
     */
    private static volatile boolean running = true;

    int i;
    volatile int j;

    public void setThreadFailed() {

    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (running) {
            }

            System.out.println("t1 end");
        }, "t1").start();


        new Thread(() -> {
            while (running) {
            }

            System.out.println("t2 end");
        }, "t2").start();

        new Thread(() -> {
            while (running) {
            }

            System.out.println("t3 end");
        }, "t3").start();


    }

}
