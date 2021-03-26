package com.waffae.pancake.integrated.interview.thread;

/**
 * 多线程环境下的经典问题-生产者和消费者问题
 * 使用if会产生虚假唤醒的问题。使用while解决
 * 1.if条件判断只会执行一次
 * 2.while循环会在循环内执行完成后,再检查一遍while的限制条件,这样能保证的逻辑的正确性。
 *
 * @author yixiaoshuang
 * @date 2020/11/29 18:10
 */
public class SynProdConsumer {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {

                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {

                }
            }
        }, "B").start();
    }

}

class Data {
    private int count;

    public void increment() throws InterruptedException {
        synchronized (this) {
            if (count != 0) {
                this.wait();
            }
            count++;
            System.out.println("thread:" + Thread.currentThread().getName() + ";count=" + count);
            this.notifyAll();
        }
    }

    public void decrement() throws InterruptedException {
        synchronized (this) {
            if (count == 0) {
                this.wait();
            }
            count--;
            System.out.println("thread:" + Thread.currentThread().getName() + ";count=" + count);
            this.notifyAll();
        }
    }
}
