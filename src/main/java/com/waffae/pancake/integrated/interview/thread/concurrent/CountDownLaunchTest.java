package com.waffae.pancake.integrated.interview.thread.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 减法计数器
 * 待数值清零后,执行主线程要做的事
 *
 * @author yixiaoshuang
 * @date 2020/12/6 16:13
 */
@Slf4j
public class CountDownLaunchTest {

    public static void main(String[] args) {
        // 初始计数
        CountDownLatch cdl = new CountDownLatch(16);

        for (int i = 0; i < 16; i++) {
            new Thread(() -> {
                log.info(Thread.currentThread().getName() + " go out：");
                // 减1操作
                cdl.countDown();
            }, String.valueOf(i)).start();
        }

        // 待计数器清零后,再向下执行
        try {
            cdl.await();
        } catch (InterruptedException e) {
        }

        // 主进程会阻塞，知道计数器清零
        log.info("done");
    }

}
