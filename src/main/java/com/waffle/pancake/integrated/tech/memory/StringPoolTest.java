package com.waffle.pancake.integrated.tech.memory;

/**
 * 字符串常量池
 *
 * @author yixiaoshuang
 * @date 2020/11/9 20:46
 */
public class StringPoolTest {
    private static int a;

    public static void main(String[] args) {
        String s1 = new String("def");
        String s2 = s1.intern();
        System.out.println(s1 == s2);
        String s3 = "def";

        // s2 对象 intern(),已经把def 放入了字符串常量池中
        System.out.println(s3 == s2);

        // 非静态的类变量,必须先初始化，才能使用
        a++;
    }
}
