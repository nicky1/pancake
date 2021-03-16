package com.waffae.pancake.integrated.interview.designmode.singleton;

import java.util.Objects;

/**
 * 懒汉式
 *
 * @author yixiaoshuang
 * @date 2020/12/10 08:51
 */
public class SafeLazyMode {

    private SafeLazyMode() {
        System.out.println("构造方法执行:" + Thread.currentThread().getName() + "ok");
    }

    private volatile static SafeLazyMode INSTANCE = null;

    public static SafeLazyMode getInstance() {
        // 双重检测锁
        if (Objects.isNull(INSTANCE)) {
            synchronized (SafeLazyMode.class){
                if (Objects.isNull(INSTANCE)){
                    // 多线程下,new操作并不是原子操作
                    INSTANCE = new SafeLazyMode();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> SafeLazyMode.getInstance()).start();
        }
    }
}
