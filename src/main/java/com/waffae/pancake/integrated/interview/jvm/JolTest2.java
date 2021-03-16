package com.waffae.pancake.integrated.interview.jvm;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 测试jvm内存布局查看工具jol
 *
 * @author yixiaoshuang
 * @date 2020/12/16 19:38
 */
public class JolTest2 {

    public static void main(String[] args) throws InterruptedException {
        // 这里为什么休眠5秒钟,就可以复现出 偏斜锁? 因为偏斜锁 默认配置的是-系统启动4秒后开启。
        TimeUnit.SECONDS.sleep(5);

        Object user = new Object();
        String s = ClassLayout.parseInstance(user).toPrintable();
        System.out.println(s);


        // synchronized锁,会使用到对象头上的mark word,这里通过jol对比观察
        synchronized (user){
            System.out.println(ClassLayout.parseInstance(user).toPrintable());
        }

        // 通过对比发现,暂停5m后，使用了synchronized关键字后,对象头的mark word都是101，偏斜锁。
    }

}
