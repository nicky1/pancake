package com.waffle.pancake.integrated.tech.thread.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2022/11/9
 **/
@Slf4j
public class InheritThreadTest {

    private static InheritableThreadLocal<String> itl = new InheritableThreadLocal();
    private static ThreadLocal<String> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        itl.set("aaaa");
        tl.set("bbb");
        new Thread(() -> {
           test1();

        }).start();

    }

    public static void test1() {
        log.info("value:{}", itl.get());
        log.info("value2:{}", tl.get());
    }
}
