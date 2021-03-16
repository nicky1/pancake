package com.waffae.pancake.integrated.interview.jvm;

/**
 * 查看jvm字节码
 *
 * @author yixiaoshuang
 * @date 2020/11/26 15:15
 */
public class JavaByteCode {

    public static void main(String[] args) {
        Integer i = null;
        Boolean a = false;
        System.out.println(a ? 0 : i);
    }

}
