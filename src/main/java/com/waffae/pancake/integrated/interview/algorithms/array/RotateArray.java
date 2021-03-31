package com.waffae.pancake.integrated.interview.algorithms.array;

/**
 * 旋转数组:给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * https://leetcode-cn.com/problems/rotate-array/
 *
 * @author yixiaoshuang
 * @date 2021/3/30 23:45
 */
public class RotateArray {

    /**
     * 旋转数组
     * nums = [1,2,3,4,5,6,7], k = 3
     * 1.先旋转整个数组
     * 2.
     */
    public static void rotate(int nums[], int k) {
        int len = nums.length;
        k = k % len;
        reverse(nums, 0, len - 1);

        reverse(nums, 0, k - 1);

        reverse(nums, k, len - 1);
    }

    private static void reverse(int nums[], int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = tmp;
        }

    }

    public static void main(String[] args) {
        int nums[] = {1, 2,3,4,5,6,7};
//        rotate(nums, 3);
//
//        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
//        }


    }

}
