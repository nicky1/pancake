package com.waffae.pancake.integrated.interview.designmode.singleton;

/**
 * 静态内部类
 *
 * @author yixiaoshuang
 * @date 2020/12/10 22:41
 */
public class Holder {

    private Holder(){
        System.out.println("init holder");
    }

    public static Holder getInstance(){
        return Inner.holder;
    }

    public static class Inner{
        private static final Holder holder = new Holder();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> Holder.getInstance()).start();
        }
    }
}
