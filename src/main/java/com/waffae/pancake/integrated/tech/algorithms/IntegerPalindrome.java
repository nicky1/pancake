package com.waffae.pancake.integrated.tech.algorithms;

/**
 * 回文数
 * https://leetcode-cn.com/problems/palindrome-number/
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 * @author yixiaoshuang
 * @date 2021/5/3 13:14
 */
public class IntegerPalindrome {

    public static void main(String[] args) {
        int a = 121;
        boolean result = isPalindrome2(a);
        System.out.println(result);
    }

    /**
     * 对数字反转
     * 1.先对 10 取模，得到余数，即最后一个数字
     * 2.同时对10 取整。
     * 3.再比较数字是否相等
     */
    public static boolean isPalindrome(int k) {
        if (k < 0) {
            return false;
        }
        int x = 0;
        int tmp = k;

        while (k != 0) {
            if (x <= Integer.MIN_VALUE || x >= Integer.MAX_VALUE) {
                return false;
            }
            int digit = k % 10;
            k = k / 10;
            x = x * 10 + digit;
        }

        return x == tmp;
    }

    /**
     * 优化版本
     * 1.不需要将数字全部反转，只需要反转一半。
     * 2.当原始数字<=反转数字时，即已经处理了一半的数字
     */
    public static boolean isPalindrome2(int k) {
        if (k < 0 || (k != 0 && k % 10 == 0)) {
            return false;
        }
        int x = 0;
        while (k >= x) {
            int digit = k % 10;
            k = k / 10;
            x = x * 10 + digit;
        }

        return (x == k) || (x / 10 == k);
    }
}
