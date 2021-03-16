package com.waffae.pancake.integrated.interview.algorithms;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode-cn.com/problems/print-in-order/
 * 3个线程,要按顺序执行 first -> second -> third 方法.
 *
 * @author yixiaoshuang
 * @date 2021/3/10 17:27
 */
public class OrderPrint {
    // 1.使用2个AtomicInteger变量的值来保证方法执行的顺序且能保证线程安全。
    private AtomicInteger first = new AtomicInteger(0);
    private AtomicInteger second = new AtomicInteger(0);

    // 2.尝试使用semaphore信号量
    private Semaphore secondSP = new Semaphore(0);
    private Semaphore thirdSP = new Semaphore(0);

    public static void main(String[] args) throws Exception{
        OrderPrint print = new OrderPrint();

        Thread t1 = new Thread(() -> print.first2(() -> System.out.println("first")));
        Thread t3 = new Thread(() -> {
            try {
                print.second2(() -> System.out.println("second"));
            } catch (InterruptedException e) {

            }
        });
        Thread t2 = new Thread(() -> {
            try {
                print.third2(() -> System.out.println("third"));
            } catch (InterruptedException e) {

            }
        });

        t3.start();
        t2.start();
        t1.start();
    }


    public void first(Runnable print) {
        print.run();
        first.incrementAndGet();
    }

    public void second(Runnable print) {
        while (first.get() != 1) {
        }
        print.run();
        second.incrementAndGet();
    }

    public void third(Runnable print) {
        while (second.get() != 1) ;
        print.run();
    }

    public void first2(Runnable print){
        print.run();
        secondSP.release();
    }

    public void second2(Runnable print) throws InterruptedException {
        secondSP.acquire();
        print.run();
        thirdSP.release();
    }

    public void third2(Runnable print) throws InterruptedException {
        thirdSP.acquire();
        print.run();
    }
}


