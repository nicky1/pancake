package com.waffae.pancake.integrated.interview.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 缓存行填充测试
 *
 * @author yixiaoshuang
 * @date 2020/12/20 13:08
 */
public class CacheLinePaddingTest {

    public static final long count = 1_0000_0000L;

    public static class T {
        public volatile long x = 0;
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
