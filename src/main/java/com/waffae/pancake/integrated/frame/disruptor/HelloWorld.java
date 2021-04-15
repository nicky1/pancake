package com.waffae.pancake.integrated.frame.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.waffae.pancake.integrated.frame.disruptor.handler.LongEventHandler;
import com.waffae.pancake.integrated.frame.disruptor.producer.LongEventProducer;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-04-02 23:48
 * @Description:
 */
public class HelloWorld {

    public static void main(String[] args) throws Exception {
        //factory of event
        LongEventFactory factory = new LongEventFactory();

        //the ringBuffer size
        int bufferSize = 8;

        //construct the disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        //connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        //start the disruptor
        disruptor.start();

        //get the ringBuffer from the disruptor to bu used publishing
        RingBuffer<LongEvent> buffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer();
        producer.setRingBuffer(buffer);

        for (long l = 0; l < 20; l++) {
            producer.onData(l + "");
            System.out.println("send data:" + l);
        }

        disruptor.shutdown();
        System.out.println("done");

    }
}
