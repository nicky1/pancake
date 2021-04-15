package com.waffae.pancake.integrated.frame.disruptor.handler;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventHandler;
import com.waffae.pancake.integrated.frame.disruptor.LongEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-04-02 23:37
 * @Description:
 */
@Slf4j
@Component
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) {
//        try {
//            log.info("handler event:{}", JSON.toJSONString(longEvent));
//        } catch (Exception e) {
//            log.error("handler ex", e);
//        }
        log.info("onevent:{},seq:{}", JSON.toJSONString(longEvent), l);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {

        }
    }
}
