package com.waffae.pancake.integrated.tech.algorithms.array;

import java.util.Arrays;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * [1,2,3,1] true
 *
 * @author yixiaoshuang
 * @date 2021/3/31 15:38
 */
public class ContainsDuplicate {

    public static boolean containsDupl(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return false;
        }

        // 先排序
        Arrays.sort(nums);
        for (int i = 0; i < len - 1; i++) {
            // 再判断相邻元素是否相等
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 4, 3};
        boolean result = containsDupl(nums);
        System.out.println(result);
    }
}
