package com.waffae.pancake.integrated.interview.thread.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 水闸
 *
 * @author yixiaoshuang
 * @date 2020/12/6 16:47
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        // 集齐7颗龙珠后,召唤神龙
        CyclicBarrier barrier = new CyclicBarrier(7, () -> System.out.println("集齐7颗龙珠后,召唤神龙"));

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
                System.out.println("收集一颗龙珠!");
                // 等待
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                } catch (BrokenBarrierException e) {
                }
            }).start();

        }

        // 待7个线程执行完成后,才会去执行barrier预置的 线程。但不会阻塞主进程的执行。
        System.out.println("done");
    }


}
