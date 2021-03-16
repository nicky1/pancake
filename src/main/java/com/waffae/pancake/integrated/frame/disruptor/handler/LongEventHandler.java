package com.waffae.pancake.integrated.frame.disruptor.handler;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventHandler;
import com.waffae.pancake.integrated.frame.disruptor.LongEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-04-02 23:37
 * @Description:
 */
@Slf4j
public class LongEventHandler implements EventHandler<LongEvent> {


    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        log.info("handler event:{},l:{},b:{}",JSON.toJSONString(longEvent),l,b);
    }
}
