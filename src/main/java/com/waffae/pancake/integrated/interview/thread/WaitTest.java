package com.waffae.pancake.integrated.interview.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试sleep与wait的区别
 * sleep:Thread类,不会释放对象锁，到时间后继续执行。
 * wait:Object方法,会放弃锁，并得到notify通知，再去获取对象锁资源。且必须使用在同步代码块中。
 * 都会让出cpu资源。
 *
 * @author yixiaoshuang
 * @date 2020/12/22 19:10
 */
public class WaitTest {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        // IllegalMonitorStateException,没有获取到锁,必须包裹在synchronized代码块中
//        object.wait();

//        synchronized (object){
//            object.wait();
//        }

        // 测试发现,使用Lock锁，也会报错。
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("lock");
            object.wait();

        } finally {
            lock.unlock();
        }
    }

}
