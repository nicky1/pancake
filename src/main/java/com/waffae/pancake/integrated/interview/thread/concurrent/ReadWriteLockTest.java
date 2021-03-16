package com.waffae.pancake.integrated.interview.thread.concurrent;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写分离锁
 * 用于优化synchronized/lock锁,提高性能
 *
 * @author yixiaoshuang
 * @date 2020/12/6 18:15
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        // 使用于读多写少的场景

        MyCacheLock map = new MyCacheLock();
        for (int i = 0; i < 3; i++) {
            final int tmp = i;
            new Thread(() -> map.put(String.valueOf(tmp), "hello"), String.valueOf(i)).start();
        }

        for (int i = 0; i < 30; i++) {
            final int tmp = i;
            new Thread(() -> map.get(String.valueOf(tmp)), String.valueOf(i)).start();
        }
    }

}

class MyCacheLock {
    private volatile Map<String, String> map = Maps.newHashMap();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 模拟写的场景
     */
    public void put(String key, String value) {
        Lock lock = readWriteLock.writeLock();

        try {
            System.out.println(Thread.currentThread().getName() + " 开始写入:" + key);
            lock.lock();
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入成功:" + key);
        } finally {
            lock.unlock();
        }
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + " 开始读取:" + key);
        map.get(key);
        System.out.println(Thread.currentThread().getName() + " 读取完成:" + key);
    }
}
