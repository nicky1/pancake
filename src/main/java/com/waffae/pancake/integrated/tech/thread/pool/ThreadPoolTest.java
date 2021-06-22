package com.waffae.pancake.integrated.tech.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试
 *
 * @author yixiaoshuang
 * @date 2021/1/9 16:24
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));

        TestThread t1 = new TestThread();

        executorService.execute(t1);

        executorService.shutdown();


    }

}

class TestThread implements Runnable {

    @Override
    public void run() {
        System.out.println("start execute TestThread");

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
        }
        System.out.println("finish TestThread");
    }
}
