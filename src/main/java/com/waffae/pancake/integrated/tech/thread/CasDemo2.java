package com.waffae.pancake.integrated.tech.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试java支持cas语法
 * 3条线程,累加到1000
 *
 * @author yixiaoshuang
 * @date 2020/12/13 15:03
 */
public class CasDemo2 {

    private static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (num.get() < 100) {
                    System.out.println("thread:" + Thread.currentThread().getName() + ";num:" + num.incrementAndGet());
                }
            }).start();
        }
    }

}
