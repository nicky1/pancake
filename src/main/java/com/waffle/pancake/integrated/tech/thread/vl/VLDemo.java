package com.waffle.pancake.integrated.tech.thread.vl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 测试
 *
 * @author yixiaoshuang
 * @date 2020/12/9 23:14
 */
public class VLDemo {

    // volatile不保证原子性
    private volatile static int num = 0;

    private static AtomicInteger num2 = new AtomicInteger(0);


    public static void main(String[] args) throws InterruptedException {

        // 测试volatile不能保证原子性
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
//                add2();
                add();
            }).start();
        }

        TimeUnit.SECONDS.sleep(3);
        System.out.println(num + ";;;;;;" + num2);
    }

    public static void add() {
        // 虽然num变量使用了volatile来修饰,但 ++ 操作不是原子操作。所以num的值不能保证原子性。
        num++;
    }

    public static void add2() {
        num2.getAndIncrement();
    }
}
