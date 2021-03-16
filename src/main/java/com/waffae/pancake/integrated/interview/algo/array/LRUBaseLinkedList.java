package com.waffae.pancake.integrated.interview.algo.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 使用链表简单模拟实现缓存LRU算法
 * 1.使用单向链表，尾部节点是 越早之前访问的。
 * 2.当有一个新数据访问时，从链表头部开始遍历
 * 如果数据之前已经缓存在 链表中了，遍历得到这条数据对应的节点，并将其从原来的位置删除，然后再插入到链表的头部。
 * 如果数据不在链表上，如果链表缓存未满，则将次节点插入到链表头部
 * 否则 将链表尾部尾部节点删除，将新的数据插入到链表的头部。
 * https://github.com/wangzheng0822/algo/blob/master/java/06_linkedlist/LRUBaseLinkedList.java
 *
 * @author yixiaoshuang
 * @date 2020/7/21 23:18
 */
@Slf4j
public class LRUBaseLinkedList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private SNode<T> headNode;
    private int length;

    private int capacity;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public void add(T t) {
        SNode preNode = findPreNode(t);

        // 链表中存在,删除原数据,即删除preNode节点下一个元素，再插入到头部,
        if (null != preNode) {
            deleteElementOptim(preNode);
        } else {
            // 链表容量超限,则删除尾部节点
            if (length > this.capacity) {
                deleteAtEnd();
            }

            // 链表中不存在且未超限,则将新数据放到链表头部
        }
        insertElemAtBegin(t);

    }

    private void printAll() {
        SNode node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    public void deleteAtEnd() {
        SNode node = headNode;

        // 没有后继节点,即空链表,直接返回
        if (node.getNext() == null) {
            return;
        }

        // 找到倒首第二个节点
        while (node.getNext().getNext() != null) {
            node = node.getNext();
        }

        node.setNext(null);
        length--;
    }

    /**
     * 删除preNode下一个节点
     */
    private void deleteElementOptim(SNode preNode) {
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /**
     * 链表头部插入节点
     */
    private void insertElemAtBegin(T data) {
        SNode next = headNode.getNext();
        SNode head = new SNode(data, next);
        headNode.setNext(head);
        length++;
    }

    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    class SNode<T> {
        private SNode next;

        private T element;

        public SNode() {
            this.next = null;
        }

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }
    }


    public static void main(String[] args) {
        LRUBaseLinkedList list = new LRUBaseLinkedList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            list.add(scanner.nextInt());
            list.printAll();
        }
    }
}
