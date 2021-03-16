package com.waffae.pancake.integrated.interview.thread;

/**
 * synchronized 是可重入锁，怎么实现的。
 *
 * @author yixiaoshuang
 * @date 2021/3/11 21:52
 */
public class TestSynchronizedReentry {

    public static void main(String[] args) {

    }

    public void m(){
        synchronized (this){
//            System.out.println("mm");
        }
    }
}
