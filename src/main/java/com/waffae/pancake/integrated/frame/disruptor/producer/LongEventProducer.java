package com.waffae.pancake.integrated.frame.disruptor.producer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.waffae.pancake.integrated.frame.disruptor.LongEvent;

import java.nio.ByteBuffer;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-04-02 23:41
 * @Description:
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
        @Override
        public void translateTo(LongEvent longEvent, long l, ByteBuffer byteBuffer) {
            longEvent.setValue(byteBuffer.getLong(0));
        }
    };

    public void onData(ByteBuffer byteBuffer){
        ringBuffer.publishEvent(TRANSLATOR,byteBuffer);
    }
}
