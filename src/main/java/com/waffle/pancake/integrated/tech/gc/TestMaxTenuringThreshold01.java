package com.waffle.pancake.integrated.tech.gc;

/**
 * 运行环境:jdk8，默认的GC: parallel GC
 * 测试年轻代经过minor gc N次后晋升到老年代的阈值(N次)
 * https://shimo.im/docs/rqKYgRjCwTDDGGCt#anchor-OD9R
 * https://www.cnblogs.com/webor2006/p/11031563.html
 * -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=5
 * 初始堆大小 最大堆大小 年轻代大小
 *
 * @author yixiaoshuang
 * @date 2021/8/10 22:16
 */
public class TestMaxTenuringThreshold01 {

    public static void main(String[] args) {
//        int size = 1024 * 1024;

        /* 1. 示例1 模拟对象分配过程发生的 young gc，full gc，内存溢出的过程*/
//        byte[] a1 = new byte[5 * size];
//        byte[] a2 = new byte[5 * size];
//        byte[] a3 = new byte[5 * size];
        // 上面运行后，会内存溢出: a1 分配在年轻代，
        // a2因为年轻代的Eden，from/to 3个区域都放不下，所以直接分配到老年代。那老年代只剩下不足5M
        // a3，要分配5M，就导致内存溢出

        /* 2. 模拟对象分配过程中,内存分配担保机制 : a4,a5对象会重新分配到老年代,a6对象在eden区分配
         *  在GC前会进行一次判断：如果要分配的内存 >= eden区 大小的一半;则直接在老年代分配内存
         *  否则进入担保分配机制:将年轻代的对象放入老年代，把当前对象(a6)在eden区分配。
         * */
//        byte[] a4 = new byte[2 * size];
//        byte[] a5 = new byte[2 * size];
//        byte[] a6 = new byte[3 * size];

        /* 3. 模拟对象分配过程中,内存分配担保机制 : 这里a61对象会直接分配在老年代。
         *  在GC前会进行一次判断：如果要分配的内存 >= eden区 大小的一半;则直接在老年代分配内存
         *  否则进入担保分配机制:将年轻代的对象放入老年代，把当前对象在eden区分配。
         * */
//        byte[] a41 = new byte[2 * size];
//        byte[] a51 = new byte[2 * size];
//        byte[] a61 = new byte[5 * size];



    }
}
