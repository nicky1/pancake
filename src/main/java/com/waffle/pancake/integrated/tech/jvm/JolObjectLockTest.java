package com.waffle.pancake.integrated.tech.jvm;


import org.openjdk.jol.info.ClassLayout;

/**
 * 测试jvm内存布局查看工具jol
 *
 *
 * @author yixiaoshuang
 * @date 2020/12/16 19:38
 */
public class JolObjectLockTest {

    public static void main(String[] args) throws InterruptedException {
        // 延迟启动,偏向锁会生效,否则对象默认是无锁状态
//        TimeUnit.SECONDS.sleep(6);

        /**
         *  这里对象的锁状态,偏向锁;
         *  最开始的8位的后3位 101 偏向锁
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
         */
        Object user = new Object();
//        String s = ClassLayout.parseInstance(user).toPrintable();
//        System.out.println(s);

        /**
         * 显示调用获取 java对象的identity hashcode，通过system.identityhashcode或obj.hashcode2种方法，即会生成identityhashcode，此时的偏向锁会失效，
         * 因为mark word放不下，所以会进行锁升级成 轻量级锁或重量级锁。
         * 1.重量级锁可以模拟同时开启t1，t2两个线程。
         */
//        int h1 = System.identityHashCode(user);
//        System.out.println(user.hashCode());

        // synchronized锁,会使用到对象头上的mark word,这里通过jol对比观察，
        // 这里仍然是 偏向锁，虽然有synchronized关键字,但对象头mark word上线程ID和当前线程ID一致,不会发生锁升级
        synchronized (user) {
            System.out.println(ClassLayout.parseInstance(user).toPrintable());
        }

        // 只开启t1线程的情况下,user对象的锁状态为 轻量级锁;轻量级锁有产生竞争,通过CAS自旋操作去获取锁,指向的是jvm虚拟机栈中的锁记录的指针
        // 并不会发生内核态的调用。
        Thread t1 = new Thread(new ThreadA(user));
        Thread t2 = new Thread(new ThreadA(user));
//        t1.start();

        // 如果开启t2线程,则会晋升为 重量级锁;多个线程竞争资源,CAS自旋一定阀值次数后,升级成重量级锁00
        // 重量级锁需要调用操作系统内核支持的锁机制，设计到内核态的切换。效率低。
//        t2.start();


        // 通过对比发现,使用了synchronized关键字后,对象头的mark word发生了改变。101-偏向锁 001-无锁 00-轻量级锁
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
