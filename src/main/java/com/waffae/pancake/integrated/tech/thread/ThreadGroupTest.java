package com.waffae.pancake.integrated.tech.thread;

/**
 * 测试一个简单的进程会有多少线程
 *
 * @author yixiaoshuang
 * @date 2020/11/30 16:44
 */
public class ThreadGroupTest {

    public static void main(String[] args) {
        String s = "hello world";
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        // main-主进程 Reference Handler-处理引用对象本身的垃圾回收 Finalizer-处理用户的finalizer方法 Signal Dispatcher-外部jvm命令的转发器
        System.out.println(s);
    }
}
