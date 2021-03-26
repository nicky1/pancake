package com.waffae.pancake.integrated.interview.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在多线程场景中,线程操作资源变化。首先要将 资源和线程操作 解耦。
 *
 * @author yixiaoshuang
 * @date 2020/11/28 10:31
 */
public class Counter {

    public static void main(String[] args) throws InterruptedException {
        CtCounter counter = new CtCounter();
//        Thread t1 = new Thread(counter::add);
//        Thread t2 = new Thread(counter::add);

        Thread t1 = new Thread(counter::add3);
        Thread t2 = new Thread(counter::add3);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        counter.print();
    }
}

/**
 * 资源-即可能有共享变量的主体。
 */
class CtCounter {
    private final ReentrantLock lock = new ReentrantLock();

    private int count;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public volatile int a;

    private AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(CtCounter.class, "a");

    public void add() {
        lock.lock();
        try {
            for (int i = 0; i < 100000; i++) {
                count++;
            }
        } finally {
            lock.unlock();
        }
    }

    public void add2() {
        for (int i = 0; i < 100000; i++) {
            count++;
        }
    }

    // 使用AtomicInteger保证原子性
    public void add3() {
        for (int i = 0; i < 100000; i++) {
            atomicInteger.getAndIncrement();
        }
    }

    public void add4() {

    }

    public void print() {
        System.out.println(atomicInteger.get());
    }

}




