package com.waffae.pancake.integrated.tech.jstack;

/**
 * 死锁分析
 *
 * @author yixiaoshuang
 * @date 2020/8/1 17:55
 */
public class JStackDemo3 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new LockClass(true));
        Thread t2 = new Thread(new LockClass(false));
        t1.start();
        t2.start();
    }


    static class LockClass implements Runnable {

        public boolean flag;

        LockClass(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                while (true) {
                    synchronized (Suo.o1) {
                        System.out.println("o1:" + Thread.currentThread());
                        synchronized (Suo.o2) {
                            System.out.println("o2:" + Thread.currentThread());
                        }
                    }
                }
            } else {
                while (true) {
                    synchronized (Suo.o2) {
                        System.out.println("o2:" + Thread.currentThread());
                        synchronized (Suo.o1) {
                            System.out.println("o1:" + Thread.currentThread());
                        }
                    }
                }
            }
        }
    }

    static class Suo {
        static Object o1 = new Object();
        static Object o2 = new Object();
    }
}
