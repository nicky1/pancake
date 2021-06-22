package com.waffae.pancake.integrated.tech.memory;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * 软引用-垃圾回收时，并不会回收软引用对象。但在分配对象内存时，如果堆内存空间不足，则会进行gc，
 * 回收软引用对象。
 *
 * @author yixiaoshuang
 * @date 2020/12/30 22:29
 */
public class SoftReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);

//        m = null;
        System.out.println(m.get());
        System.gc();

        TimeUnit.SECONDS.sleep(1);

        System.out.println(m.get());

        SoftReference<byte[]> m2 = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(m.get());
    }
}
