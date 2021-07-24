package com.waffle.pancake.integrated.tech.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 缓存行填充测试
 * 1.应用在 disruptor，concurrenthashmap中。
 * 2.在jdk1.8中，可以使用注解 @sum.misc.Contended,同时jvm启动要加上参数：-XX:-RestrictContended
 *
 * @author yixiaoshuang
 * @date 2020/12/20 13:08
 */
public class CacheLinePaddingTest2 {

    public static final long count = 1_0000_0000L;

    /**
     * 在CPU高速缓存中,缓存的最小单元是cache line,而一个cache line的存储大小是64个字节。这里使用空间换时间的策略。
     * 填充满一个缓存行,保证变量在多线程环境下,变量不会处于同一个缓存行。这样对共享变量的修改不会产生同步。
     */
    public static class T {
//                public long p1, p2, p3, p4, p5, p6, p7;
//        @sun.misc.Contended
        public volatile long x = 0;
//        public long p9, p10, p11, p12, p13, p14, p15;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        // 启动2个线程,每个线程分别修改数组的2个元素
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                arr[0].x = i;
            }
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            for (int i = 0; i < count; i++) {
                arr[1].x = i;
            }
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();

        long end = System.nanoTime();
        System.out.println((end - start) / 100_0000);

    }
}
