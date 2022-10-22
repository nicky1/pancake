package com.waffle.pancake.controller;

import com.alibaba.ttl.TtlRunnable;
import com.waffle.pancake.dto.UserInfo;
import com.waffle.pancake.util.thread.ThreadContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * @desc: TTL线程池测试，1:线程池使用TTL封装 2:runnable对象使用ttl封装。
 * @author: yixiaoshuang
 * @date: 2022/10/22
 **/
@RestController
@Slf4j
public class TestTtlController {

    @Resource
    private ExecutorService commonThreadPool;

    @GetMapping("/test/ttl/get")
    public ResponseEntity testTTL(@RequestParam Integer userId) {
        UserInfo user = ThreadContext.getUser();
        Integer contextValue = Objects.isNull(user) ? 0 : user.getUserId();
        log.info("传入的userId和从线程上下文获取的是否一致,flag:{},传入userId:{},上下文获取:{}", (userId.equals(contextValue)), userId, contextValue);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test/ttl/runnable")
    public ResponseEntity ttlRunnable(@RequestParam Integer userId) {
        Runnable runnable = () -> {
            UserInfo user = ThreadContext.getUser();
            Integer contextValue = Objects.isNull(user) ? 0 : user.getUserId();
            log.info("传入的userId和从线程上下文获取的是否一致2,flag:{},传入userId:{},上下文获取:{}", (userId.equals(contextValue)), userId, contextValue);
        };
        TtlRunnable ttlRunnable = TtlRunnable.get(runnable);
        runnable.run();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test/pool")
    public ResponseEntity pool(@RequestParam Integer userId) throws InterruptedException {
        Runnable runnable = () -> {
            UserInfo user = ThreadContext.getUser();
            Integer contextValue = Objects.isNull(user) ? 0 : user.getUserId();
            log.info("传入的userId和从线程上下文获取的是否一致2,flag:{},传入userId:{},上下文获取:{}", (userId.equals(contextValue)), userId, contextValue);
        };
        CompletableFuture.runAsync(runnable, commonThreadPool);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/test/ttl/pool")
    public ResponseEntity ttlPool(@RequestParam Integer userId) {
        Runnable runnable = () -> {
            UserInfo user = ThreadContext.getUser();
            Integer contextValue = Objects.isNull(user) ? 0 : user.getUserId();
            log.info("传入的userId和从线程上下文获取的是否一致2,flag:{},传入userId:{},上下文获取:{}", (userId.equals(contextValue)), userId, contextValue);
        };
        CompletableFuture.runAsync(TtlRunnable.get(runnable), commonThreadPool);
        return ResponseEntity.ok().build();
    }
}
