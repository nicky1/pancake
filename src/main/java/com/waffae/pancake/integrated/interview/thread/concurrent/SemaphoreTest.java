package com.waffae.pancake.integrated.interview.thread.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 * 线程可以等待,适用于 限流 。
 *
 * @author yixiaoshuang
 * @date 2020/12/6 17:45
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        // 线程数量,模拟 3个车位,6辆车排队入坑。
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 持有锁
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    // 模拟停车占用时间
                    TimeUnit.SECONDS.sleep(2);

                    // 时间到了后,开走,释放一个坑,其它的车来占坑
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"释放车位");
                } catch (Exception e) {
                }
            }, String.valueOf(i)).start();

        }
    }

}
