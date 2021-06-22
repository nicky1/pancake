package com.waffae.pancake.integrated.tech.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 测试cas ABA问题,使用cas reference带版本号解决
 *
 * @author yixiaoshuang
 * @date 2020/12/11 21:33
 */
public class CASDemo {

    public static void main(String[] args) throws Exception {
        // 开启2个线程模拟ABA 问题
        AtomicStampedReference<Integer> atomicRef = new AtomicStampedReference(1, 1);

        new Thread(() -> {
            int stamp = atomicRef.getStamp();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

            }

            System.out.println(atomicRef.compareAndSet(1, 2, atomicRef.getStamp(), atomicRef.getStamp() + 1));

            System.out.println(atomicRef.compareAndSet(2, 1, atomicRef.getStamp(), atomicRef.getStamp() + 1));

            System.out.println("a1:" + atomicRef.getStamp());

        }, "a").start();

        new Thread(() -> {
            int stamp = atomicRef.getStamp();

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }

            System.out.println(atomicRef.compareAndSet(1, 3, stamp, stamp + 1));

            System.out.println("b1:" + atomicRef.getStamp());
        }, "b").start();
    }

}
