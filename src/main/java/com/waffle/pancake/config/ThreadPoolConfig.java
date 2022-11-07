package com.waffle.pancake.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2022/10/22
 **/
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "commonThreadPool")
    public ExecutorService commonThreadPool() {
        ExecutorService executor = new ThreadPoolExecutor(10,15,1000, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(200),initFactory("test"));
        executor = TtlExecutors.getTtlExecutorService(executor);
        return executor;
    }

    private ThreadFactory initFactory(String poolName) {
        return new DefaultThreadFactory(poolName);
    }
}
