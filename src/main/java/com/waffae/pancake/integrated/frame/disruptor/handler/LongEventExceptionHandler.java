package com.waffae.pancake.integrated.frame.disruptor.handler;

import com.lmax.disruptor.ExceptionHandler;
import com.waffae.pancake.integrated.frame.disruptor.LongEvent;

/**
 * @author yixiaoshuang
 * @date 2021/4/15 15:29
 */
public class LongEventExceptionHandler implements ExceptionHandler<LongEvent> {

    @Override
    public void handleEventException(Throwable ex, long sequence, LongEvent event) {
        System.out.println("ex:"+sequence);

    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}
