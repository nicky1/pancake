package com.waffae.pancake.integrated.interview.jvm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 测试java方法参数传递使用集合，在方法内部，对入参的集合重新赋值成一个新对象。但不会改变main方法体内list集合内容。
 * 因为java方法参数是 值传递。
 *
 * @author yixiaoshuang
 * @date 2021/2/25 21:03
 */
public class ArrayReferenceTest {

    public static void main(String[] args) {
        String appPath = System.getProperty("java.class.path");
        String appPath2 = appPath.replaceAll(":",System.lineSeparator());
        System.out.println(appPath2);

        List<Integer> list = Lists.newArrayList(1);
        list.add(2);

        ArrayReferenceTest referenceTest = new ArrayReferenceTest();
        System.out.println(referenceTest);
        referenceTest.func(list);

        for (Integer i : list){
            System.out.println("a:"+i);
        }
    }

    public void func(List<Integer> list){
//        list = list.stream().filter(v -> v >1).collect(Collectors.toList());

        for (Integer i : list){
//            System.out.println("b:"+i);
        }
        list.add(4);
    }
}
