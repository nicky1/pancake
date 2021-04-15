package com.waffae.pancake.controller;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.waffae.pancake.integrated.frame.disruptor.LongEvent;
import com.waffae.pancake.integrated.frame.disruptor.producer.LongEventProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yixiaoshuang
 * @date 2021/4/14 14:11
 */
@RestController
public class DisruptorController {

    @Resource
    private Disruptor<LongEvent> disruptor;

    @Resource
    private LongEventProducer producer;

    @GetMapping(value = "/api/v1/disruptor/send")
    public ResponseEntity testDisruptor(@RequestParam String batchNo){
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        producer.setRingBuffer(ringBuffer);
        producer.onData(batchNo);
        System.out.println("controler send data:"+batchNo);
        return ResponseEntity.ok().build();
    }
}
