package com.waffae.pancake.integrated.interview.thread;

import java.util.concurrent.TimeUnit;

/**
 * https://mp.weixin.qq.com/s/k2_YTklXidhs_apcGZN9zg
 * 深入学习 Thread.sleep(),有几个问题
 * 1.sleep会释放锁吗
 * 2.sleep结束后，立即会 唤醒吗
 * 3.sleep(0) 有什么作用？
 * 4.sleep和wait的区别？
 *
 * @author yixiaoshuang
 * @date 2021/3/4 19:03
 */
public class DeepenSleep {
    // 用一个变量赋值的顺序，来测试方法的执行流程
    public static int number = 100;


    public static void main(String[] args) throws InterruptedException {
        DeepenSleep demo = new DeepenSleep();
        Thread thread = new Thread(() -> demo.m1());

        thread.start();
        demo.m2();

        System.out.println("num3:"+ number);
    }

    public void m1() {
        synchronized (this) {
            System.out.println("m1");
            System.out.println("num:" + number);

            number += 888;
            System.out.println("m1 number"+number);
        }
    }


    public void m2() throws InterruptedException {
        synchronized (this) {
            System.out.println("m2");

            TimeUnit.SECONDS.sleep(2);

            System.out.println("sleep done");

            number *= 100;
            System.out.println("m2 number:"+number);
        }
    }
}
