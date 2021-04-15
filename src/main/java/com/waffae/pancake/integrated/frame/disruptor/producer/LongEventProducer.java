package com.waffae.pancake.integrated.frame.disruptor.producer;

import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;
import com.waffae.pancake.integrated.frame.disruptor.LongEvent;
import org.springframework.stereotype.Component;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-04-02 23:41
 * @Description:
 */
@Component
public class LongEventProducer {
    private  RingBuffer<LongEvent> ringBuffer;

    public void setRingBuffer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorVararg<LongEvent> translator = (event, sequence, args) -> event.setValue((String) args[0]);

    public void onData(String value) {
        ringBuffer.publishEvent(translator, value);
    }
}
