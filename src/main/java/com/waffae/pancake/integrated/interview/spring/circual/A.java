package com.waffae.pancake.integrated.interview.spring.circual;

/**
 * @author yixiaoshuang
 * @date 2021/3/21 15:41
 */
public class A {
    private B b;

    public void setB(B b) {
        this.b = b;
    }

    public B getB() {
        return b;
    }
}
