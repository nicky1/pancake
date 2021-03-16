package com.waffae.pancake.integrated.interview.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试synchronized关键字
 *
 * @author yixiaoshuang
 * @date 2020/11/27 08:58
 */
@Slf4j
public class HotProductService {

    public void serviceA() {
        synchronized (this) {
            log.info("serviceA,start1 at :{}", System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            log.info("serviceA,start2 at :{}", System.currentTimeMillis());
        }
    }

    public void serviceB() {
        synchronized (this) {
            log.info("serviceB,start1 at :{}", System.currentTimeMillis());
        }
    }

    /**
     * 测试锁住的是Class,即使不同线程创建了Class类不同的实例，也会存在同步现象。
     */
    public void serviceC() {
        synchronized (HotProductService.class) {
            log.info("synchronized class serviceC");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            log.info("synchronized class sleep serviceC");
        }
    }

    /**
     * 测试锁住的是Class,即使不同线程创建了Class类不同的实例，也会存在同步现象。
     */
    public void serviceC2() {
        synchronized (HotProductService.class) {
            log.info("synchronized class33 serviceC");
        }
    }

    public static void main(String[] args) {
        // 1.synchronized(this):锁住的是对象,如果2个线上使用的是同一个对象实例,则会出现线程同步的情况,即2个线程不能同时执行。
        HotProductService service = new HotProductService();
//        ThreadA threadA = new ThreadA(service);
//        ThreadB threadB = new ThreadB(service);
//
//        threadA.start();
//        threadB.start();

        // 2.这里测试synchronized(this):锁住是2个不同的对象，则2个线程可以同时执行。不是线程安全的。
//        HotProductService service1 = new HotProductService();
//        ThreadB threadB2 = new ThreadB(service1);
//        threadB2.start();

        // 3.测试锁住的是 X.class
        ThreadC threadA3 = new ThreadC(service);
        // 即使2个线程传入的是2个不同的对象实例,这里也是线程同步的。
        HotProductService service3 = new HotProductService();
        ThreadC2 threadB3 = new ThreadC2(service3);
        threadA3.start();
        threadB3.start();

        // 4.使用自定义对象时,不同线程必须为同一对象，否则不能保证同步运行，不能保证线程安全。

    }
}

class ThreadA extends Thread {

    private HotProductService service;

    public ThreadA() {
    }

    public ThreadA(HotProductService service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceA();
    }
}

class ThreadB extends Thread {
    private HotProductService service;

    public ThreadB() {

    }

    public ThreadB(HotProductService service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceB();
    }
}

class ThreadC extends Thread {
    private HotProductService service;

    public ThreadC() {

    }

    public ThreadC(HotProductService service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceC();
    }
}

class ThreadC2 extends Thread {
    private HotProductService service;

    public ThreadC2() {

    }

    public ThreadC2(HotProductService service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceC2();
    }
}