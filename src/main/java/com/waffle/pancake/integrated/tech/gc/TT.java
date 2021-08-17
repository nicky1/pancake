package com.waffle.pancake.integrated.tech.gc;

/**
 * 测试年轻代经过minor gc N次后晋升到老年代的阈值(N次)
 * https://www.cnblogs.com/webor2006/p/11031563.html
 * -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=5
 * 初始堆大小 最大堆大小 年轻代大小
 * @author yixiaoshuang
 * @date 2021/8/10 22:16
 */
public class TT {


    public static void main(String[] args) {

        Integer a = new Integer(1);

        synchronized (a){

        }
    }
}
