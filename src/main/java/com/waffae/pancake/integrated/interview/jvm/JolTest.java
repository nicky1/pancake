package com.waffae.pancake.integrated.interview.jvm;


import org.openjdk.jol.info.ClassLayout;

/**
 * 测试jvm内存布局查看工具jol
 *
 * @author yixiaoshuang
 * @date 2020/12/16 19:38
 */
public class JolTest {

    public static void main(String[] args) {
//        User user = new User();

        Object user = new Object();
        String s = ClassLayout.parseInstance(user).toPrintable();
        System.out.println(s);

        // synchronized锁,会使用到对象头上的mark word,这里通过jol对比观察
        synchronized (user){
            System.out.println(ClassLayout.parseInstance(user).toPrintable());
        }

        // 通过对比发现,使用了synchronized关键字后,对象头的mark word发生了改变。001-无锁 00-轻量级锁
        // 00000001
        // 10001000
    }

}
