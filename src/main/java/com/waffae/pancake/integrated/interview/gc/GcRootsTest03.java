package com.waffae.pancake.integrated.interview.gc;

/**
 * GCRoots测试-方法区类静态变量作为GC Roots
 * https://blog.csdn.net/u010798968/article/details/72835255
 * 方法区存储的是 类信息，常量，静态变量。即时编译器编译后的代码。
 * <p>
 * -Xms128m -Xmx128m -XX:+PrintGCDetails
 *
 * @author yixiaoshuang
 * @date 2020/11/12 21:56
 */
public class GcRootsTest03 {
    private static final int _10MB = 10 * 1024 * 1024;

    private byte[] memory;

    private static GcRootsTest03 t;

    public GcRootsTest03() {
    }

    public GcRootsTest03(int size) {
        memory = new byte[size];

    }

    public static void main(String[] args) {
        GcRootsTest03 test = new GcRootsTest03(2 * _10MB);
        GcRootsTest03.t = new GcRootsTest03(8 * _10MB);

        test = null;

        // test对象在full gc 后内存会被回收，但 t 对象为类的静态对象变量,存在于方法区中,会被作为GC Roots对象，不会被回收。
        System.gc();

    }

}
