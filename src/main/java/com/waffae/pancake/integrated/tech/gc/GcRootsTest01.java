package com.waffae.pancake.integrated.tech.gc;

/**
 * GCRoots测试-虚拟机栈，存储的是局部变量
 * <p>
 * <p>
 * -Xms128m -Xmx128m -XX:+PrintGCDetails
 *
 * @author yixiaoshuang
 * @date 2020/11/12 21:56
 */
public class GcRootsTest01 {


    private int _10MB = 10 * 1024 * 1024;

    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) {
        m1();
        System.out.println("返回main方法");

        System.gc();
        // 2.m1方法执行完成,局部变量test随机消失。不再有引用类型指向该对象。full gc后，对象被回收。老年代回收test对象占用的空间。83836K->1595K(125952K)

        System.out.println("第二次GC完成");
    }

    public static void m1() {
        GcRootsTest01 test = new GcRootsTest01();
        System.gc();
        // 1.执行m1()方法,test是局部变量，存储在虚拟机栈中,test持有类变量属性。所以即使执行了full gc方法，memory对象转移到了老年代中。且full gc也不会被回收。83886K->83772K(125952K)
        System.out.println("第一次GC完成");
    }
}
