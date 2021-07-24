package com.waffle.pancake.integrated.tech.jvm;

import com.waffle.pancake.model.T;
import org.openjdk.jol.info.ClassLayout;

/**
 * 好奇对象分配的过程，大对象和小对象是怎么去计算的
 * # WARNING: Unable to attach Serviceability Agent. You can try again with escalated privileges. Two options: a) use -Djol.tryWithSudo=true to try with sudo; b) echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
 * com.waffae.pancake.model.T object internals:
 *  OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
 *       0     4                    (object header)                           01 a1 ad ae (00000001 10100001 10101101 10101110) (-1364352767)
 *       4     4                    (object header)                           5f 00 00 00 (01011111 00000000 00000000 00000000) (95)
 *       8     4                    (object header)                           92 c3 00 f8 (10010010 11000011 00000000 11111000) (-134167662)
 *      12     4   java.lang.String T.userId                                  (object)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 *
 * java.lang.String object internals:
 *  OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
 *       0     4          (object header)                           01 7a 10 a3 (00000001 01111010 00010000 10100011) (-1559201279)
 *       4     4          (object header)                           30 00 00 00 (00110000 00000000 00000000 00000000) (48)
 *       8     4          (object header)                           da 02 00 f8 (11011010 00000010 00000000 11111000) (-134216998)
 *      12     4   char[] String.value                              [6, 0, 6, 1, 6, 8, 0, 6, c, 9, 2, 2, 8, 7, 4, 7, e, 3, 3, b, 9, d, 7, 8]
 *      16     4      int String.hash                               0
 *      20     4          (loss due to the next object alignment)
 * Instance size: 24 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * @author yixiaoshuang
 * @date 2021/3/30 15:52
 */
public class TestObjectSize {

    public static void main(String[] args) {
//        User user = new User();
//        user.setUserid("60616806c9228747e33b9d78");
        String userId = "60616806c9228747e33b9d78";

        String tprintable2 = ClassLayout.parseClass(T.class).toPrintable();
        System.out.println(tprintable2);

        T t = new T();
//        t.setUserId(userId);

        long instancesize = ClassLayout.parseInstance(t).instanceSize();
        System.out.println(instancesize);

//        // 测试对象t占用的内存大小-对象头(mark word：8个字节 指向类的类型指针-元数据指针：4个字节) 12个字节，实例数据-string类型，开启压缩指针后占用4个字节，即共 12+4=16个字节。
        String tprintable = ClassLayout.parseInstance(t).toPrintable();
        System.out.println(tprintable);
//
//        String printable = ClassLayout.parseInstance(userId).toPrintable();
//        System.out.println(printable);
//
//        T2 t2 = new T2();
//        String s3 = ClassLayout.parseInstance(t2).toPrintable();
//        System.out.println(s3);
//
//        // 对象及其引用占用内存大小,这里会将t2对象的str属性占用的内存大小一起统计进去，大概=3mb，同时可以结合jprofiler去验证
//        long size4 = RamUsageEstimator.sizeOf(t2);
//        System.out.println(size4);
//
//        // 对象本身占用内存大小:对象头+实例数据
//        long size5 = RamUsageEstimator.shallowSizeOf(t2);

    }
}
