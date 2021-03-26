package com.waffae.pancake.integrated.interview.limit;

/**
 * @author yixiaoshuang
 * @date 2021/3/21 21:52
 */
public abstract class Limiter {

    private int qps;

    Limiter(int qps) {
        this.qps = qps;
    }

}
