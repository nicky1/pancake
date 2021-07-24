package com.waffle.pancake.integrated.tech.jvm;

/**
 * @author yixiaoshuang
 * @date 2021/3/30 19:21
 */
public class T5 {

    String s;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public T5(){
        System.identityHashCode(s);
    }
}
