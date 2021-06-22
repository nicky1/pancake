package com.waffae.pancake.integrated.tech.jvm;

/**
 * 测试java运行时数据区域-栈帧 局部变量表
 *
 * @author yixiaoshuang
 * @date 2021/3/4 23:07
 */
public class TestPlus {

    public static void main(String[] args) {
        int i = 10;
//        i = i++;
        int j = i++;
        System.out.println(i);
//        System.out.println(j);


    }

    public void foo(Object lock) {
        synchronized (lock) {
            lock.hashCode();
        }
    }

}
