package com.waffle.pancake.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.waffle.pancake.util.thread.ThreadContext;
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
        ExecutorService executor = new ThreadPoolExecutor(2,4,1000, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(20),initFactory(new AtomicInteger()));
        executor = TtlExecutors.getTtlExecutorService(executor);
        return executor;
    }

    private ThreadFactory initFactory(AtomicInteger atomicInteger) {
        return r->{
            Runnable inner = () ->{
                try {
                    r.run();
                }finally {
                    ThreadContext.remove();
                }
            };
            Thread t = new Thread(inner);
            t.setName("test-common-"+atomicInteger.getAndIncrement());
            t.setDaemon(false);
            return t;
        };
    }
}
