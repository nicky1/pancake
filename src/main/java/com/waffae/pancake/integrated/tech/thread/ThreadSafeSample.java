package com.waffae.pancake.integrated.tech.thread;

/**
 * @author yixiaoshuang
 * @date 2020/11/26 22:47
 */
public class ThreadSafeSample {

    public int counter = 1;

    public void nonSafeAction() {
        while (counter < 1000000) {
            int former = counter++;
            int latter = counter;
            if (former != latter - 1) {
                System.out.println("former:{}" + former + ";latter:" + latter);
            }

        }
    }

    public void safeAction() {
        while (counter < 100000) {
            synchronized (this) {
                int former = counter++;
                int latter = counter;
                if (former != latter - 1) {
                    System.out.println("former:{}" + former + ";latter:" + latter);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSample simple = new ThreadSafeSample();

        Thread t1 = new Thread(() -> simple.safeAction());

        Thread t2 = new Thread(() -> simple.safeAction());

        t1.start();
        t2.start();

        // join-在主线程main中,将当前线程t1加入到main线程中。执行完t1后，再执行main线程。
        t1.join();
        t2.join();
        System.out.println("done");
    }
}
