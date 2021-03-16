package com.waffae.pancake.integrated.interview.jvm;


import com.waffae.pancake.model.User;

/**
 * JVM逃逸分析-并不是所有对象都是在堆中创建，jvm优化机制;如锁消除
 * 关闭 -XX:-DoEscapeAnalysis
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
        System.out.println(t2-t1);
    }

    public static void alloc(int i){
        User user = new User();
        user.setUserid("a");
        user.setEnname("zhangsan");
    }
}

