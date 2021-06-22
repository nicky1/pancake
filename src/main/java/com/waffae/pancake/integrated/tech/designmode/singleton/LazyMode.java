package com.waffae.pancake.integrated.tech.designmode.singleton;

import java.util.Objects;

/**
 * 懒汉式
 *
 * @author yixiaoshuang
 * @date 2020/12/10 08:51
 */
public class LazyMode {

    private LazyMode() {
        System.out.println("构造方法执行:" + Thread.currentThread().getName() + "ok");
    }

    private static LazyMode INSTANCE = null;

    public static LazyMode getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new LazyMode();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 单线程模式下
        LazyMode instance = LazyMode.getInstance();
        LazyMode instance2 = LazyMode.getInstance();

        System.out.println(instance);
        System.out.println(instance2);


    }
}
