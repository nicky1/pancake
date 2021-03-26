package com.waffae.pancake.integrated.interview.spring.circual;

/**
 * @author yixiaoshuang
 * @date 2021/3/21 15:41
 */
public class B {
    private C c;

    public void setC(C c) {
        this.c = c;
    }

    public C getC() {
        return c;
    }
}
