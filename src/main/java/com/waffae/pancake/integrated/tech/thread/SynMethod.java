package com.waffae.pancake.integrated.tech.thread;

/**
 * @author yixiaoshuang
 * @date 2020/12/4 16:47
 */
public class SynMethod {

    public synchronized void test() {
        System.out.println("test");
    }

    public void m() {
        synchronized (this) {

        }
    }


    public static void main(String[] args) {

    }
}
