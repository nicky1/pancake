package com.waffae.pancake.integrated.interview.algorithms;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * @author yixiaoshuang
 * @date 2021/5/3 13:14
 */
public class IntegerReverse {

    public static void main(String[] args) {
        int a = 1534236469;
        int x = integerReverse(a);
        System.out.println(x);
//        System.out.println(Integer.MAX_VALUE);


    }

    /**
     * 对数字反转
     * 1.先对 10 取模，得到余数，即最后一个数字
     * 2.同时对10 取整。
     */
    public static int integerReverse(int k){
        int x = 0;

        while (k != 0){
            if (x <= Integer.MIN_VALUE || x >= Integer.MAX_VALUE){
                return 0;
            }
            int digit = k % 10;
            k= k / 10;
            x = x * 10 + digit;
        }

        return x;
    }
}
