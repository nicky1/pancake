package com.waffle.pancake.integrated.tech.designmode.singleton;

/**
 * 饿汉式
 *
 * @author yixiaoshuang
 * @date 2020/12/10 08:48
 */
public class Hungry {

    // 单例模式,要保证构造方法私有
    private Hungry() {
    }

    private static final Hungry INSTANCE = new Hungry();

    // 提供对外获取对象的静态方法
    public static Hungry getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Hungry instance = Hungry.getInstance();
        Hungry instance2 = Hungry.getInstance();

        System.out.println(instance);
        System.out.println(instance2);
    }
}
