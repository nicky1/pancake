package com.waffae.pancake.integrated.interview.collection;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/8/6 23:17
 * @Description:
 */
@Slf4j
public class TestArrayList{

  public static void main(String[] args) {


      LinkedList linkedList = Lists.newLinkedList();
      linkedList.add(1);
      linkedList.add(3);
      linkedList.add(0);


      linkedList.stream().forEach(v -> {
          log.info("data : {}", v);
      });

      List list = Lists.newArrayList(1, 5, 2, 8, 4);
      System.out.println(list);

      list.add(3, 3);
      System.out.println(list);

  }

}
