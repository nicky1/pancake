package com.waffle.pancake.integrated.tech.thread;

import java.util.concurrent.TimeUnit;

/**
 * 测试sleep与wait的区别
 * sleep:Thread类,不会释放对象锁，到时间后继续执行。
 * wait:Object方法,会放弃锁，并得到notify通知，再去获取对象锁资源。且必须使用在同步代码块中。
 * 都会让出cpu资源。
 *
 * @author yixiaoshuang
 * @date 2020/12/22 19:10
 */
public class WaitAndSleepTest {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();

        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
                System.out.println("aaa");
            } catch (InterruptedException e) {
            }
        }, "a");

        thread.start();

        Thread thread2 = new Thread(() -> {
            System.out.println("bbb");

        }, "b");

        thread2.start();

    }

}
