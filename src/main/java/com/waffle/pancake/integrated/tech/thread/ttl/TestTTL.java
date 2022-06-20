package com.waffle.pancake.integrated.tech.thread.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 测试阿里开源的多线程框架TTL，解决在线程池场景下,线程间传递变量串的问题。
 * @author: xsyi
 * @date: 2022-06-07
 */
public class TestTTL {


    /**
     * 测试父子线程，threadLocal 值传递
     */
    public void testParentThread() {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("value parent");

        Thread thread = new Thread(() -> {
            String s = context.get();
            System.out.println(s);
        });
        thread.start();
    }


    /**
     * 待验证，线程池场景，怎么写测试用例
     */
    public void testThreadFactory() {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("value parent");

        ExecutorService executorService = new ThreadPoolExecutor(10, 15, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));

        for (int i = 0; i < 20; i++) {
            context.set("value parent");

            Runnable task = () -> System.out.println(context.get());
            if (i < 1) {
                context.set("s1");

                TtlRunnable ttlRunnable = TtlRunnable.get(task);
                executorService.submit(ttlRunnable);

            }else{
                context.set("s2");
                TtlRunnable ttlRunnable = TtlRunnable.get(task);

                executorService.submit(ttlRunnable);
            }

        }

    }

    public static void main(String[] args) {
        TestTTL test = new TestTTL();
//        test.testParentThread();

        test.testThreadFactory();
    }
}
