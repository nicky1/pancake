package com.waffae.pancake.integrated.interview.jvm;

/**
 * jvm即时编译优化测试
 *
 * @author yixiaoshuang
 * @date 2020/12/16 18:04
 */
public class JvmCompileTest {

    public static void main(String[] args) {
        int a = 2;
        int b = 1;
        if (a > b) {
            b = 3;
        } else {
            b = 4;
        }

//        System.out.println(b);
    }


}
