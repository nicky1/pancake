package com.waffae.pancake.integrated.interview.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试ThreadPoolExecutor源码,验证流程。
 *
 * @author yixiaoshuang
 * @date 2021/3/6 15:06
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 2L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        executor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 20; i++) {
            executor.execute(new WT1());
//            TimeUnit.SECONDS.sleep(1);
        }
    }

}


class WT1 implements Runnable {

    @Override
    public void run() {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//        }
//        System.out.println("task:"+Thread.currentThread().getName());
        System.out.println(1 / 0);
    }
}

