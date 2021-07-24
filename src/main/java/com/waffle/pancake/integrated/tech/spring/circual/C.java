package com.waffle.pancake.integrated.tech.spring.circual;

/**
 * @author yixiaoshuang
 * @date 2021/3/21 15:41
 */
public class C {
    private A a;

    public void setA(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }
}
