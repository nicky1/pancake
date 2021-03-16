package com.waffae.pancake.integrated.interview.thread;

import java.util.concurrent.TimeUnit;

/**
 * 1.sleep和wait的区别？
 *
 * @author yixiaoshuang
 * @date 2021/3/4 19:03
 */
public class DeepenWait {
    // 用一个变量赋值的顺序，来测试方法的执行流程
    public static int number = 100;

    private static Object lock = new Object();


    public static void main(String[] args) throws InterruptedException {
        DeepenWait demo = new DeepenWait();
        Thread thread = new Thread(() -> {
            try {
                demo.doWait();
            } catch (InterruptedException e) {

            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(2);
        demo.doNotify();

    }

    /**
     * 1.调用wait方法后,会释放对象的锁,这样doNotify方法才能获取到锁。
     * 2.doNotify()方法 执行notifyALL方法,会唤醒所有当前lock对象锁 等待的线程。
     *   线程被唤醒后，要重新去获取锁。
     * 3.注意 推荐使用 notifyAll方法，因为哪个线程获取到锁，依赖操作系统的随机性，
     *   notify方法 会唤醒一个线程。避免有的线程永远获取不到锁，而醒不过来了。
     */
    public void doWait() throws InterruptedException {
        synchronized (lock) {
            System.out.println("wait start");
            lock.wait();
            System.out.println("wait end");
        }
    }


    public void doNotify() throws InterruptedException {
        synchronized (lock) {
            System.out.println("notify start");

            lock.notifyAll();
            System.out.println("notify end");
            TimeUnit.SECONDS.sleep(3);

        }
    }
}
