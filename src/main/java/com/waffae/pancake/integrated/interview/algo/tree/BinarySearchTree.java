package com.waffae.pancake.integrated.interview.algo.tree;

/**
 * 二叉树-搜索
 *
 * @author yixiaoshuang
 * @date 2020/10/28 22:48
 */
public class BinarySearchTree {

    private Node tree;

    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (p.data == data) {
                return p;
            } else if (p.data > data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        return null;
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

    }
}
