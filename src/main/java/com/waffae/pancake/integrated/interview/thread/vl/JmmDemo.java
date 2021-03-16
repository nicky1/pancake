package com.waffae.pancake.integrated.interview.thread.vl;

import java.util.concurrent.TimeUnit;

/**
 * jmm-java内存模型
 *
 * @author yixiaoshuang
 * @date 2020/12/9 22:08
 */
public class JmmDemo {

    // volatile能保证内存可见性
    private volatile static int num = 0;

    /**
     * 测试线程工作内存和主存,数据同步
     */
    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            while (num == 0){
            }
        }).start();

        TimeUnit.MILLISECONDS.sleep(10);
        num = 1;
        System.out.println(num);

    }
}
