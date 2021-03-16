package com.waffae.pancake.integrated.frame.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-04-02 23:34
 * @Description:
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
