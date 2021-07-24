package com.waffle.pancake.integrated.tech.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 2个资源对象，2个线程，2个线程方法是安全的。那个方法会先被执行?
 * 这里是2把不同的锁，彼此之间不受同步方法影响。按照时间顺序执行。
 *
 * @author yixiaoshuang
 * @date 2020/11/30 21:58
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        new Thread(() -> phone1.sendSms(), "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {

        }
        new Thread(() -> phone2.call(), "B").start();


    }
}

class Phone2 {
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

    // 没有锁，不受同步方法的影响
    public void hello() {
        System.out.println("hello");
    }
}
