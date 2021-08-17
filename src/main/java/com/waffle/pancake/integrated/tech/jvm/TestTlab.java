package com.waffle.pancake.integrated.tech.jvm;

/**
 * jvm内存分配优化机制：-XX:UseTLAB
 *
 *
 * @author yixiaoshuang
 * @date 2021/8/12 21:20
 */
public class TestTlab {

    public static void main(String[] args) {
        // 1.TLAB机制是会为每个线程初始化一块内存空间,所以如果应用程序线程过多，也会额外占用一部分空间
        // 通过在开启和关闭TLAB的情况，可以发现 eden区占用内存空间大小是不一样的。

        // 2.-XX:+DoEscapeAnalysis 参数是开启逃逸分析,如果对象只在方法内分配，不会逃逸出方法体。
        // 则会把对象分配在栈上，进入栈帧。方法执行结果，则会弹出。同时也不会触发GC,且分配更快。 jvm的一种优化机制。

        TestTlab t = new TestTlab();
        int i =0;

        while (true){
            t.allocate(i);
        }

    }

    private void allocate(int i){
        new User("21",i);
    }
}

class User{
    private String name;

    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
