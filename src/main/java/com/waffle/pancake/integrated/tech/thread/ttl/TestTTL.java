package com.waffle.pancake.integrated.tech.thread.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 测试阿里开源的多线程框架TTL，解决在线程池场景下,线程间传递变量串的问题。
 * @author: yxs
 * @date: 2022-06-07
 */
public class TestTTL {

    int j = 0;

    TransmittableThreadLocal<String> context = new TransmittableThreadLocal(){

        @Override
        public String copy(Object parentValue) {
            return new String(String.valueOf(parentValue));
        }
    };

    /**
     * 测试父子线程，threadLocal 值传递
     */
    public void testParentThread() {
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal(){

        };
        context.set("value parent");

        Thread thread = new Thread(() -> {
            String s = context.get();
            System.out.println(s);
        });
        thread.start();
    }


    /**
     * 待验证，线程池场景，怎么写测试用例`
     */
    public void testThreadFactory() {
//        InheritableThreadLocal context = new InheritableThreadLocal();
        context.set("value parent");

        ExecutorService executorService = new ThreadPoolExecutor(2, 3, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));

//        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(executorService);

        for (int i = 0; i < 5; i++) {

            Runnable task = () -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                System.out.println(context.get());
            };

            context.set("s2:"+(++j));
            TtlRunnable ttlRunnable = TtlRunnable.get(task);
            executorService.submit(ttlRunnable);

            context.set("s2:"+(++j));

        }
        System.out.println("end");

    }

    public static void main(String[] args) throws Exception {
        TestTTL test = new TestTTL();
//        test.testParentThread();

        test.testThreadFactory();
    }
}
