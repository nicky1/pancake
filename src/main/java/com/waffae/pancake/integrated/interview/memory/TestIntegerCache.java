package com.waffae.pancake.integrated.interview.memory;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-04-10 16:08
 * @Description:测试integer cache
 */
@Slf4j
public class TestIntegerCache {
    public static void main(String[] args) {

        // -Djava.lang.Integer.IntegerCache.high=1000
        //-XX:AutoBoxCacheMax=20000
        Integer a = 10000;
        String value = sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        log.info(value);

        Integer b = Integer.valueOf(120000);

        List<String> list = Lists.newArrayList("11");
        test1(list);
        for(String s : list){
            System.out.println(s);
        }

    }

    public static void test1(List<String> list){
        list.add("22");
    }

}
