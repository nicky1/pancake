package com.waffle.pancake.integrated.tech.gc;

/**
 * 引用计数回收算法
 *
 * @author yixiaoshuang
 * @date 2020/11/12 16:08
 */
public class ReferenceCountGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占点内存，以便在能在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    /**
     * 控制台输出内存回收日志
     * -Xms32m -Xmx32m -XX:+PrintGCDateStamps  -XX:+PrintGCDetails -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime
     * 日志：[Full GC (System.gc()) [PSYoungGen: 1014K->0K(9728K)] [ParOldGen: 901K->1297K(22016K)] 1915K->1297K(31744K), [Metaspace: 3246K->3246K(1056768K)], 0.0083946 secs]
     * 结论:虽然objA,objB 互相持有引用。1931K->1382K(31744K), 内存被回收掉了，说明没有采用引用计数算法来判断对象是否存活。
     */
    public static void testGC() {
        ReferenceCountGC objA = new ReferenceCountGC();
        ReferenceCountGC objB = new ReferenceCountGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        // 假设在这行发生GC，objA和objB是否能被回收？
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {


        testGC();
    }

}
