package com.waffae.pancake.integrated.interview.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程环境下的经典问题-生产者和消费者问题
 * 1.使用lock以及condition实现生产者和消费者场景。
 *
 * @author yixiaoshuang
 * @date 2020/11/29 18:10
 */
public class ConditionLockProdConsumer {

    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {

                }
            }
        }, "A").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {

                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {

                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {

                }
            }
        }, "D").start();
    }

}

class Data3 {
    private int count;

    private Lock lock = new ReentrantLock();
    // 使用Condition替代Object的wait和notify
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (count !=0){
                condition.await();
            }
            count++;
            System.out.println("thread:" + Thread.currentThread().getName() + ";count=" + count);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0){
                condition.await();
            }
            count--;
            System.out.println("thread:" + Thread.currentThread().getName() + ";count=" + count);
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }
}
