package com.waffle.pancake.integrated.tech.algorithms;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/12/3 22:43
 * @Description: 链表反转
 */
@Slf4j
public class ReverseNode {


    public static class TestNode<T> {
        public T node;
        public TestNode next;

        public TestNode(T node, TestNode next) {
            this.node = node;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        TestNode t1 = new TestNode("d", null);
        TestNode t2 = new TestNode("c", t1);
        TestNode t3 = new TestNode("b", t2);
        TestNode t4 = new TestNode("a", t3);
        LinkedList<TestNode> l1 = Lists.newLinkedList();
        l1.add(t1);
        l1.add(t2);
        l1.add(t3);
        l1.add(t4);

        for (TestNode t : l1) {
            log.info(JSON.toJSONString(t));
        }

        TestNode cur = t4;
        TestNode pre = null;
        while (cur != null) {
            TestNode nextNode = cur.next; //临时节点存储，下一个
            cur.next = pre; //当前节点 指向前一个节点
            pre = cur;      //前节点，到 现任节点
            cur = nextNode; //现任节点 到 下一个节点

        }
        log.info(JSON.toJSONString(pre));
        log.info("a:" + JSON.toJSONString(l1));


    }

}
