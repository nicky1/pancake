package com.waffae.pancake.integrated.tech.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 2个资源对象，2个线程，2个线程方法是安全的。那个方法会先被执行?
 * synchronized修饰静态代码块或Class对象。
 *
 * @author yixiaoshuang
 * @date 2020/11/30 21:58
 */
public class Test3 {
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        new Thread(() -> phone1.sendSms(), "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {

        }
        new Thread(() -> phone2.call(), "B").start();


    }
}

class Phone3 {
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }
        System.out.println("发短信");
    }

    public static synchronized void call() {
        System.out.println("打电话");
    }

}
