package com.waffae.pancake.integrated.tech.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程环境下的经典问题-生产者和消费者问题
 * 0.达到线程交替顺序执行
 * 1.这里的生产者和消费者执行没有顺序，期望是 A执行完了执行B，B执行完了执行C，C执行完了执行A
 * 2.考虑使用Condition实现线程的精准通知唤醒。
 *
 * @author yixiaoshuang
 * @date 2020/11/29 18:10
 */
public class LockProdConsumer4 {

    public static void main(String[] args) {
        Data4 data = new Data4();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printA();
                } catch (InterruptedException e) {
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printB();
                } catch (InterruptedException e) {
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.printC();
                } catch (InterruptedException e) {
                }
            }
        }, "C").start();

    }

}

class Data4 {
    private Lock lock = new ReentrantLock();
    // 目标是通过Condition实现精准通知唤醒,这里需要定义多个condition变量。
    private Condition condition = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    // 1-A 2-B 3-C
    private int number = 1;

    public void printA() throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            number = 2;
            System.out.println("thread:" + Thread.currentThread().getName() + ";AAAAAAAA");
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printB() throws InterruptedException {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            number = 3;
            System.out.println("thread:" + Thread.currentThread().getName() + ";BBBBBB");
            condition3.signal();
        } finally {
            lock.unlock();
        }

    }

    public void printC() throws InterruptedException {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            number = 1;
            System.out.println("thread:" + Thread.currentThread().getName() + ";CCCCCCC");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
