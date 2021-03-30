package com.waffae.pancake.integrated.interview.jvm;


import com.waffae.pancake.model.User;

/**
 * JVM逃逸分析-并不是所有对象都是在堆中创建，jvm优化机制;如锁消除,栈上分配，标量替换等。
 * 关闭 -XX:-DoEscapeAnalysis
 *
 * // 使用server模式 最大堆空间为15m 初始堆空间为15m 启用逃逸分析 打印ＧＣ日志 关闭TLAB 启用标量替换，允许对象打散分配到栈上
 * -server -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB -XX:+EliminateAllocations
 *
 * //关闭逃逸分析 关闭TLAB 启用标量替换，不会讲对象分配到栈上。
 * -server -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB -XX:+EliminateAllocations
 *
 * //启用逃逸分析 关闭TLAB 关闭标量替换
 * -server -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB -XX:-EliminateAllocations
 *
 * @author yixiaoshuang
 * @date 2021/2/24 23:13
 */
public class EscapeAnalysis {

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc(i);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    /**
     * jvm运行时JIT编译优化-User对象的栈上分配，分配速度快。
     * 那为什么栈上分配会快？？？
     * 栈上分配的好处：1.分配速度快 2.因为在栈上，所以方法执行完成，弹出栈帧，自动回收锁分配的内存空间，这样就不用垃圾回收器来处理不再被引用的对象。
     */
    public static void alloc(int i) {
        User user = new User();
        user.setUserid("a");
        user.setEnname("zhangsan");
    }
}

