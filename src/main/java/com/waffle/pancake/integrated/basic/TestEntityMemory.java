package com.waffle.pancake.integrated.basic;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 测试实体类中 基本数据类型和 包装类型的内存对比。
 * 包装类型存储在对象内的是一个引用，持有4个字节，而long持有8个字节。
 * https://blog.csdn.net/thekenofDIS/article/details/51274942
 *
 * @author yixiaoshuang
 * @date 2020/9/8 23:10
 */
public class TestEntityMemory {
    public static void main(String[] args) {
//        List<EntityWrapper> list = Lists.newArrayList();
//        int i =0;
//        while (i < Integer.MAX_VALUE){
//            System.out.println(i++);
//            list.add(new EntityWrapper());
//        }
        //156962

//        List<EntityLongBasic> list = Lists.newArrayList();
//        int i =0;
//        while (i < Integer.MAX_VALUE){
//            System.out.println(i++);
//            list.add(new EntityLongBasic());
//        }
        //106710

        List<EntityIntegerWrapper> list = Lists.newArrayList();
        int i = 0;
        while (i < Integer.MAX_VALUE) {
            System.out.println(i++);
            list.add(new EntityIntegerWrapper());
        }
        //157400

//        List<EntityIntBasic> list = Lists.newArrayList();
//        int i =0;
//        while (i < Integer.MAX_VALUE){
//            System.out.println(i++);
//            list.add(new EntityIntBasic());
//        }
        //156948  157603
    }
}
