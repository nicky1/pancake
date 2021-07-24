package com.waffle.pancake.integrated.tech.gc;

/**
 * GCRoots测试-方法区常量引用对象作为GC Roots
 * https://blog.csdn.net/u010798968/article/details/72835255
 * 方法区存储的是 类信息，常量，静态变量。即时编译器编译后的代码。
 * <p>
 * -Xms128m -Xmx128m -XX:+PrintGCDetails
 *
 * @author yixiaoshuang
 * @date 2020/11/12 21:56
 */
public class GcRootsTest04 {
    private static final int _10MB = 10 * 1024 * 1024;

    private byte[] memory;

    private static final GcRootsTest04 t = new GcRootsTest04(5 * _10MB);

    public GcRootsTest04() {
    }

    public GcRootsTest04(int size) {
        memory = new byte[size];
    }

    public static void main(String[] args) {
        // t对象 static final 为类常量引用对象，不会被回收
        System.gc();

    }

}
