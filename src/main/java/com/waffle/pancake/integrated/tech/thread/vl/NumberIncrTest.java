package com.waffle.pancake.integrated.tech.thread.vl;

/**
 * i++ 字节码,非原子操作
 *
 * @author yixiaoshuang
 * @date 2021/8/30 21:48
 */
public class NumberIncrTest {

    private static int a = 1;

    public static void main(String[] args) {
        a+=2;
    }
}
