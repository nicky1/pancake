package com.waffle.pancake.integrated.tech.jvm;


import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 测试jvm内存布局查看工具jol
 *
 *
 * @author yixiaoshuang
 * @date 2020/12/16 19:38
 */
public class JolTest {

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);

        Object user = new Object();
        String s = ClassLayout.parseInstance(user).toPrintable();
        System.out.println(s);

        /**
         * 显示调用获取 java对象的identity hashcode，通过system.identityhashcode或obj.hashcode2种方法，即会生成identityhashcode，此时的偏向锁会失效，
         * 因为mark word放不下，所以会进行锁升级成 轻量级锁或重量级锁。
         * 1.重量级锁可以模拟同时开启t1，t2两个线程。
         */
        int h1 = System.identityHashCode(user);
//        System.out.println(user.hashCode());

        // synchronized锁,会使用到对象头上的mark word,这里通过jol对比观察
//        synchronized (user) {
//            System.out.println(ClassLayout.parseInstance(user).toPrintable());
//        }

        Thread t1 = new Thread(new ThreadA(user));
//        Thread t2 = new Thread(new ThreadA(user));
        t1.start();
//        t2.start();


        // 通过对比发现,使用了synchronized关键字后,对象头的mark word发生了改变。001-无锁 00-轻量级锁
        // 00000001
        // 10001000

        // 如下是 轻量级锁 -最开始的8位的后2位 00
//        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//        0     4        (object header)                           60 e9 88 08 (01100000 11101001 10001000 00001000) (143190368)
//        4     4        (object header)                           00 70 00 00 (00000000 01110000 00000000 00000000) (28672)
//        8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
    }

}

class ThreadA implements Runnable{

    private Object obj;

    public ThreadA(){}

    public ThreadA(Object obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}
