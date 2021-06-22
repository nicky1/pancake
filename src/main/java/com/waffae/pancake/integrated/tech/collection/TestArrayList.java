package com.waffae.pancake.integrated.tech.collection;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/8/6 23:17
 * @Description:
 */
@Slf4j
public class TestArrayList {

    public static void main(String[] args) {


//      LinkedList linkedList = Lists.newLinkedList();
//      linkedList.add(1);
//      linkedList.add(3);
//      linkedList.add(0);
//
//
//      linkedList.stream().forEach(v -> {
//          log.info("data : {}", v);
//      });
//
//      List list = Lists.newArrayList(1, 5, 2, 8, 4);
//      System.out.println(list);
//
//      list.add(3, 3);
//      System.out.println(list);
//
//      Object[] objects = new Object[2];
//      System.out.println(objects.length);

        TestArrayList test = new TestArrayList();
        test.t2();

    }

    public void t2() {
        for (int i = 0; i < t1(); i++) {
            System.out.println("i:" + i);

        }
    }

    public int t1() {
        System.out.println("1");
        return 5;
    }

}
