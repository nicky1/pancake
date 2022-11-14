package com.waffle.pancake.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.waffle.pancake.util.thread.ThreadContext2;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2022/10/22
 **/
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "commonThreadPool")
    public ExecutorService commonThreadPool() {
        ExecutorService executor = new ThreadPoolExecutor(2, 15, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(200), initFactory("test"));
        executor = TtlExecutors.getTtlExecutorService(executor);
        return executor;
    }

    @Bean(name = "commonThreadPool2")
    public ExecutorService commonThreadPool2() {
        ExecutorService executor = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(200), initFactory2(new AtomicInteger()));
        return executor;
    }

    private ThreadFactory initFactory(String poolName) {
        return new DefaultThreadFactory(poolName);
    }

    private ThreadFactory initFactory2(AtomicInteger atomicInteger) {
        return r -> {
            Runnable inner = () -> {
                try {
                    System.out.println("111");

                    r.run();
                } finally {
                    System.out.println("222");

                    ThreadContext2.remove();
                }
            };
            Thread t = new Thread();
            t.setName("test-common-" + atomicInteger.getAndIncrement());
            t.setDaemon(false);
            return t;
        };
    }
}
