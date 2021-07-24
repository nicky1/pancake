package com.waffle.pancake.integrated.tech.limit;

/**
 * 计数器限流算法
 * 每秒内计数
 *
 * @author yixiaoshuang
 * @date 2021/3/21 21:49
 */
public class CounterLimiter extends Limiter {

    /**
     * 上次记录的时间戳
     */
    private long lastTime;

    /**
     * 阈值
     */
    private int count;

    public CounterLimiter(int qps) {
        super(qps);
        this.lastTime = 0;
        this.count = 0;
    }
}
