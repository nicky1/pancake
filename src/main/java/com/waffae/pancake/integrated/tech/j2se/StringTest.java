package com.waffae.pancake.integrated.tech.j2se;

/**
 * @author yixiaoshuang
 * @date 2020/12/15 22:00
 */
public class StringTest {

    public static void main(String[] args) {
        String s1 = "zs3zs4";
        String s3 = "zs3";

        String s4 = "zs4";

        // 通过javap反编译后查看,这里使用到了 stringbuilder。因为string是final修饰不可变对象。
        String s5 = s3 + s4;

        System.out.println(s1 == s5);

    }


}
