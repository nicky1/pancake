package com.waffle.pancake.jmh;

import com.waffle.pancake.integrated.frame.jmh.PS;
import org.openjdk.jmh.annotations.Benchmark;

/**
 * @author yixiaoshuang
 * @date 2021/4/15 20:28
 */
public class PSTest {

    @Benchmark
    public void testForEach(){
        PS.foreach();
    }


}
