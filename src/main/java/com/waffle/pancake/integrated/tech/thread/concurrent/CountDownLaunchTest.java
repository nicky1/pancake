package com.waffle.pancake.integrated.tech.thread.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 减法计数器
 * 待数值清零后,执行主线程要做的事
 *
 * @author yixiaoshuang
 * @date 2020/12/6 16:13
 */
@Slf4j
public class CountDownLaunchTest {

    public static void main(String[] args) throws InterruptedException {
        // 初始计数
        CountDownLatch cdl = new CountDownLatch(4);

        for (int i = 0; i < 4; i++) {

            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {

                }
                log.info(Thread.currentThread().getName() + " go out：");
                // 减1操作
                cdl.countDown();
            }, String.valueOf(i)).start();
        }

        // 待计数器清零后,再向下执行
        try {
//            cdl.await();
            // 模拟await方法：超过timeout时间后,计数器到达0，返回true，否则返回false，且释放阻塞状态。
            boolean result = cdl.await(4, TimeUnit.SECONDS);
            if (!result){
                log.info("done2");
            }
        } catch (InterruptedException e) {

        }

        // 主进程会阻塞，直到计数器清零
        log.info("done");
    }

}
