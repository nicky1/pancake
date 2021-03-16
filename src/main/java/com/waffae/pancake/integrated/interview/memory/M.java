package com.waffae.pancake.integrated.interview.memory;

/**
 * @author yixiaoshuang
 * @date 2020/12/30 22:18
 */
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize gc");
    }
}
