package com.waffle.pancake.integrated.basic;

/**
 * @author yixiaoshuang
 * @date 2020/8/4 17:09
 */
public class ByteDes {
    static int a = 1;

    static {
        a = 2;
    }

    public static void main(String[] args) {
        System.out.println(a);
        String s = "";
    }

}
