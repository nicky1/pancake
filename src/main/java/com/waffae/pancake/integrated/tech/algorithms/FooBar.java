package com.waffae.pancake.integrated.tech.algorithms;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * https://leetcode-cn.com/problems/print-foobar-alternately/
 * 多线程-交替打印
 *
 * @author yixiaoshuang
 * @date 2021/3/10 09:35
 */
public class FooBar {

    private int n;

    FooBar(int n) {
        this.n = n;
    }

    // 1.使用CyclicBarrier水闸加上标记位,2个线程就设置为2。
    CyclicBarrier cb = new CyclicBarrier(2);
    private volatile boolean flag = true;

    // 2.使用Semaphore信号量
    Semaphore fooS = new Semaphore(1);
    // 入参设置为0，代表所有线程调用acquire都会被阻塞住。
    Semaphore barS = new Semaphore(0);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        FooBar fooBar = new FooBar(3);
        Thread t1 = new Thread(() -> {
//            try {
//                fooBar.printFoo(()-> System.out.print("foo"));
//            } catch (Exception e) {
//            }

            try {
                fooBar.printFoo1(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
            }
        });

        Thread t2 = new Thread(() -> {
//            try {
//                fooBar.printBar(()-> System.out.print("bar"));
//            } catch (Exception e){
//            }

            try {
                fooBar.printBar1(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
            }
        });

        t2.start();
        t1.start();
    }

    /**
     * 1.循环内判断标记位的值，要使用while循环
     * 2.首次，while条件不满足，退出，执行第一次打印foo，重置标记位，水闸位+1
     */
    public void printFoo(Runnable printFoo) throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!flag) {
            }
            printFoo.run();
            flag = false;
            cb.await();
        }
    }

    /**
     * 3.另一个线程：水闸位+1，此时 满足水闸阀值。执行打印bar,再重置标记位。
     */
    public void printBar(Runnable printBar) throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < n; i++) {
            cb.await();

            printBar.run();
            flag = true;
        }
    }


    public void printFoo1(Runnable r1) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            fooS.acquire();
            r1.run();
            barS.release();
        }
    }

    public void printBar1(Runnable r1) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            barS.acquire();
            r1.run();
            fooS.release();
        }
    }
}


