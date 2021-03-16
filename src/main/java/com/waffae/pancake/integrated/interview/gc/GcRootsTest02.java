package com.waffae.pancake.integrated.interview.gc;

/**
 * GCRoots测试-方法区
 * https://blog.csdn.net/u010798968/article/details/72835255
 * 方法区存储的是 类信息，常量，静态变量。即时编译器编译后的代码。
 * <p>
 * -Xms128m -Xmx128m -XX:+PrintGCDetails
 *
 * @author yixiaoshuang
 * @date 2020/11/12 21:56
 */
public class GcRootsTest02 {
    private static  int _10MB = 10 * 1024 * 1024;

    private  byte[] memory;

    public GcRootsTest02() {
    }

    public GcRootsTest02(int size) {
        memory = new byte[size];

    }

    public static void main(String[] args) {
        GcRootsTest02 test = new GcRootsTest02(2 * _10MB);

        test = null;
        // test主动置为NULL,full gc 会回收test对象占用的内存 [ParOldGen: 8K->1852K(524288K)] 1966K->1852K(983040K)
        System.gc();

    }

}
