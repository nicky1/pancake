package com.waffae.pancake.integrated.interview.thread.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 1个普通synchronized，一个静态synchronized修饰。1个对象？
 * 1.因为是2个不同的锁，肯定是没有sleep的先执行。
 *
 * @author yixiaoshuang
 * @date 2020/11/30 21:58
 */
public class Test4 {
    public static void main(String[] args) {
        Phone4 phone1 = new Phone4();

        new Thread(() -> phone1.sendSms(), "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {

        }
        new Thread(() -> phone1.call(), "B").start();


    }
}

class Phone4 {
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
        }
        System.out.println("发短信");
    }

    public  synchronized void call() {
        System.out.println("打电话");
    }

}
