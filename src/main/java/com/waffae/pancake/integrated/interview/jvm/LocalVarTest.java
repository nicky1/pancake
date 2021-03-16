package com.waffae.pancake.integrated.interview.jvm;

/**
 * 测试局部变量表槽复用对垃圾收集的影响
 * 深入理解java虚拟机第三版-403,8.1
 *
 * @author yixiaoshuang
 * @date 2020/12/15 14:57
 */
public class LocalVarTest {

    public static void main(String[] args) {
        // vm启动脚本中增加 -verbose:gc,打印GC日志


        // 1.主动执行GC 后,placeholder占用的空间并没有被回收,这是因为执行GC时,变量placeHolder还处于方法作用域内。
//        test1();

        {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
        }
        // 2.在代码块中定义变量,执行GC后,内存空间仍然没有被回收,是为什么呢?
        // 2.1 虽然已经离开了placeHolder的作用域,但是之后没有发生过对局部变量表的读写操作,变量原本占用的槽没有被复用。
        // 所以GC ROOTS仍然保持着对它的关联,没有被及时回收。大部分情况下,影响甚微。
        // 但有一种场景:已经离开了大对象的作用域,但后面的执行代码耗时很长,前面又定义了大对象时，可能会造成内存溢出。可以手动将对象置为null,覆盖之前占用的局部变量表槽。

        // 手动将变量置为NULL,或随意访问一个变量
        int a = 0;
        System.out.println(a);
        System.gc();


    }

    public static void test1() {
        byte[] placeHolder = new byte[64 * 1024 * 1024];
        System.gc();
    }

}
