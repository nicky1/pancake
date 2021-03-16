package com.waffae.pancake.integrated.interview.jvm;

/**
 * @author yixiaoshuang
 * @date 2020/12/14 17:32
 */
public class ClassStaticInitTest {

    static {
        i = 2;
    }

    static int i =1;

    public static void main(String[] args) {
        System.out.println(i);
    }

}
