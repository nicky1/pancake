package com.waffae.pancake.integrated.tech.thread;

/**
 * 多线程环境下的经典问题-生产者和消费者问题
 * 使用if会产生虚假唤醒的问题。使用while解决
 * 1.if条件判断只会执行一次
 * 2.while循环会在循环内执行完成后,再检查一遍while的限制条件,这样能保证的逻辑的正确性。
 * 3.在上一个测试代码中，只有1个生产者，1个消费者。这时能达到预期的目的,即生成和消费不会错乱
 * 4.如果有多个生产者和消费者呢？这时发现count值有大于1的。这时要使用while循环。
 * 5.在资源类中实现线程安全常规流程：判断条件---->执行---->通知
 *
 * @author yixiaoshuang
 * @date 2020/11/29 18:10
 */
public class SynProdConsumer2 {

    public static void main(String[] args) {
        Data2 data = new Data2();
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

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {

                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {

                }
            }
        }, "D").start();
    }

}

class Data2 {
    private int count;

    public void increment() throws InterruptedException {
        synchronized (this) {
            while (count != 0) {
                this.wait();
            }
            count++;
            System.out.println("thread:" + Thread.currentThread().getName() + ";count=" + count);
            this.notifyAll();
        }
    }

    public void decrement() throws InterruptedException {
        synchronized (this) {
            while (count == 0) {
                this.wait();
            }
            count--;
            System.out.println("thread:" + Thread.currentThread().getName() + ";count=" + count);
            this.notifyAll();
        }
    }
}
