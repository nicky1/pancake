package com.waffae.pancake.integrated.tech.algo.array;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-03-18 22:48
 * @Description:利用数组实现类似链表
 */
public class Array {

    private int data[];
    private int len;
    private int count;

    public Array(int len) {
        this.len = len;
        data = new int[len];
        this.count = 0;
    }

    public void insert(int index, int value) {
        //判断数组空间是否已满
        if (count == len) {
            return;
        }
        if (index < 0 || index > count) {
            return;
        }
        for (int i = count; i > index; --i) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        ++count;
    }

    private int find(int index) {
        if (index < 0 || index >= count) {
            return -1;
        }
        return data[index];
    }

    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Array array = new Array(5);
        array.printAll();
        array.insert(0, 3);
        array.printAll();
        array.insert(0, 4);
        array.printAll();
        array.insert(1, 5);
//        array.insert(3, 9);
//        array.insert(3, 10);
        //array.insert(3, 11);
        array.printAll();
    }
}
