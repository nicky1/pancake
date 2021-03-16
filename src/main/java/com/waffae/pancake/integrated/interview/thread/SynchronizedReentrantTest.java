package com.waffae.pancake.integrated.interview.thread;

/**
 * synchronized支持可重入测试
 *
 * @author yixiaoshuang
 * @date 2020/12/22 20:02
 */
public class SynchronizedReentrantTest {
    private int i = 0;

    /**
     * 同一个类的同一个方法,支持可重入性。递归调用,m1方法会执行多次
     *
     */
    public synchronized void m1() {
        System.out.println("m1,i=" + i);
        while (i < 10) {
            i++;
            m1();
        }
    }

    /**
     * 同一个类的不同方法,同样支持可重入性.
     */
    public synchronized void m2(){
        System.out.println("m2");
        m3();
    }

    public synchronized void m3(){
        System.out.println("m3");
    }

    public static void main(String[] args) {
        SynchronizedReentrantTest test = new SynchronizedReentrantTest();
//        test.m1();

        test.m2();
    }

}
