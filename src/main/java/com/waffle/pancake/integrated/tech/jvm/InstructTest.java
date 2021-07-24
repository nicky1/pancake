package com.waffle.pancake.integrated.tech.jvm;

/**
 * 字节码指令
 *
 * @author yixiaoshuang
 * @date 2020/12/2 16:14
 */
public class InstructTest {

    public void testMonitor(String f) {
        synchronized (f) {
            System.out.println("1");
        }
    }

    public synchronized void testMonitor2(String f) {
        System.out.println("1");
    }
}
