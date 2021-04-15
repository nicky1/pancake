package com.waffae.pancake.config;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.waffae.pancake.integrated.frame.disruptor.LongEvent;
import com.waffae.pancake.integrated.frame.disruptor.handler.LongEventExceptionHandler;
import com.waffae.pancake.integrated.frame.disruptor.handler.LongEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author yixiaoshuang
 * @date 2019/10/30 17:30
 */
@Configuration
public class DisruptorConfig {
    private static final int BUFFER_SIZE = 1024;

    @Resource
    private LongEventHandler longEventHandler;

    @Bean
    public Disruptor<LongEvent> longEventDisruptor() {
        Disruptor<LongEvent> longEventDisruptor = new Disruptor<>(LongEvent::new, BUFFER_SIZE, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new BlockingWaitStrategy());
        longEventDisruptor.handleEventsWith(longEventHandler);
        longEventDisruptor.setDefaultExceptionHandler(new LongEventExceptionHandler());
        longEventDisruptor.start();
        return longEventDisruptor;
    }
}
