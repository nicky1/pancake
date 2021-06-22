package com.waffae.pancake.integrated.tech.jstack;

/**
 * 分析jstack命令
 * http://www.hollischuang.com/archives/110
 *
 * @author yixiaoshuang
 * @date 2020/8/1 17:39
 */
public class JStackDemo2 {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread());
        thread.start();
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("thread");
            }
        }
    }

}
