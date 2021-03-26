package com.waffae.pancake.integrated.interview.memory;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * https://www.cnblogs.com/paddix/p/5309550.html
 *
 * @Author: yixiaoshuang
 * @Date: 2018/8/30 23:53
 * @Description: 虚拟机栈
 */
@Slf4j
public class VirtualStackTest {

    private static Integer index = 1;

    //stackofflowerror
    public void call() {
        index++;
        call();
    }

    //outofmerory error
    public void testOOM() {
        List list = Lists.newArrayList();
        while (true) {
            list.add("222");
        }

    }

    /**
     * 当栈调用深度>jvm 所允许的范围，会抛出 stackoverflow 的异常，不过这个范围不是一个恒定的值
     * 1.需要注意这里捕获的是 throwable，而不是 exception。
     * 2.还有一种错误：当申请不到空间时，会抛出 outofmeroryerror。
     *
     * @param args
     */
    public static void main(String[] args) {
        VirtualStackTest c = new VirtualStackTest();
        //stackofflowerror
//        try {
//            c.call();
//        }catch (Throwable e){
//            System.out.println("Stack deep : "+index);
//            log.error("exp:{}",e);
//        }

        c.testOOM();

    }
}
