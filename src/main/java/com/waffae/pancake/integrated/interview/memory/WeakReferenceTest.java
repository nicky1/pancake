package com.waffae.pancake.integrated.interview.memory;

import java.lang.ref.WeakReference;

/**
 * 弱引用-垃圾回收，则会释放弱引用对象。
 * 使用场景-
 *  1.ThreadLocal,希望使用完之后，能立即被垃圾回收器回收，可以将threadlocal对象置为null,防止出现内存泄漏。
 *      * key设置为null，但value不为null,且key为null，指向value的引用没有了，仍然会有可能发生内存泄漏。
 *      * 解决的办法是 调用 remove方法。
 *  2.同时，在线程池中，建议不要使用threadlocal。因为
 *      * 防止在使用完成后，忘记执行remove方法，可能会导致串数据。线程池会被复用。
 *
 * @author yixiaoshuang
 * @date 2020/12/30 22:54
 */
public class WeakReferenceTest {

    public static void main(String[] args) {

        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();

        System.out.println(m.get());

    }

}
