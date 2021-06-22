package com.waffae.pancake.integrated.tech.jstack;

/**
 * 模拟学习jstack命令,主要是分析线程死循环排查过程
 *
 * @author yixiaoshuang
 * @date 2020/8/1 17:31
 */
public class JStackDemo {

    public static void main(String[] args) {
        while (true) {
            System.out.println("loop");
        }
    }

}
