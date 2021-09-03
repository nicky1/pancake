package com.waffle.pancake.integrated.tech.thread.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

/**
 * 多线程顺序打印ABC
 *
 * @author yixiaoshuang
 * @date 2021/8/31 19:52
 */
public class AbcSemaphoreTest {

    private static Semaphore a = new Semaphore(1);
    private static Semaphore b = new Semaphore(1);
    private static Semaphore c = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {
        // 此处很重要;先让b，c都主动获取一次信号量,这样后续在多线程ThreadB,ThreadC中将会阻塞。
        // 这样能保证ThreadA中的A先被打印出来
        b.acquire();
        c.acquire();
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
        new Thread(new ThreadC()).start();
    }

    static class ThreadA implements Runnable{

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                a.acquire();
                System.out.print("A");
                b.release();
            }
        }
    }

    static class ThreadB implements Runnable{

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                b.acquire();
                System.out.print("B");
                c.release();
            }
        }
    }

    static class ThreadC implements Runnable{

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                c.acquire();
                System.out.print("C");
                a.release();
            }
        }
    }
}

