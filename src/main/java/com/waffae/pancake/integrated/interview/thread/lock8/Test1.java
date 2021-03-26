package com.waffae.pancake.integrated.interview.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 一个资源对象，2个线程，2个线程方法是安全的。那个方法会先被执行?
 *
 * @author yixiaoshuang
 * @date 2020/11/30 21:58
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> phone.sendSms(), "A").start();

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//
//        }
        new Thread(() -> phone.call(), "B").start();

        new Thread(() -> phone.hello(), "C").start();

    }
}

class Phone {
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }
        System.out.println("send sms");
    }

    public synchronized void call() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        System.out.println("call");
    }

    // 没有锁，不受同步方法的影响
    public void hello() {
        System.out.println("hello");
    }
}
