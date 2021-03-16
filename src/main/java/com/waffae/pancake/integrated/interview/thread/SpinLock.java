package com.waffae.pancake.integrated.interview.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 测试自定义自旋锁,基于CAS
 *
 * @author yixiaoshuang
 * @date 2020/12/13 11:57
 */
public class SpinLock {

    private AtomicReference<Thread> ref = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println("my lock:" + thread.getName());

        // 自旋-类似于未获取到锁,一直等待
        while (!ref.compareAndSet(null, thread)) {
//            System.out.println("cannot get lock:" + thread.getName());

        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        System.out.println("my unlock:" + thread.getName());

        // 释放锁
        ref.compareAndSet(thread, null);
    }

}
