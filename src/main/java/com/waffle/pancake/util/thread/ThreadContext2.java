package com.waffle.pancake.util.thread;

/**
 * @desc:
 * @author: yixiaoshuang
 * @date: 2022/10/22
 **/
public class ThreadContext2 {
    private static final String USER_KEY = ThreadContext2.class.getName() + "_USER_KEY";

    private static InheritableThreadLocal<String> ttl = new InheritableThreadLocal();

    public static void remove() {
        ttl.remove();
    }

    public static String getValue() {
        return ttl.get();
    }

    public static void set(String value) {
        ttl.set(value);
    }

}
