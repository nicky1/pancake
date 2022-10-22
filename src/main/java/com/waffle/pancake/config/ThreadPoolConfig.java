package com.waffle.pancake.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
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
        ExecutorService executor = new ThreadPoolExecutor(2,4,10000, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(2));
        executor = TtlExecutors.getTtlExecutorService(executor);
        return executor;
    }
}
