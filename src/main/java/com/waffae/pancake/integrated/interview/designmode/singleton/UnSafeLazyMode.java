package com.waffae.pancake.integrated.interview.designmode.singleton;

import java.util.Objects;

/**
 * 懒汉式
 *
 * @author yixiaoshuang
 * @date 2020/12/10 08:51
 */
public class UnSafeLazyMode {

    private UnSafeLazyMode() {
        System.out.println("构造方法执行:" + Thread.currentThread().getName() + "ok");
    }

    private static UnSafeLazyMode INSTANCE = null;

    public static UnSafeLazyMode getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new UnSafeLazyMode();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 多线程模式下:LazyMode可能会被初始化多次,不能达到单例的目的。
        for (int i = 0; i < 10; i++) {
            new Thread(() -> UnSafeLazyMode.getInstance()).start();
        }
    }
}
