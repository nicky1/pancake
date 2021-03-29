package com.waffae.pancake.integrated.interview.algorithms.array;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Leetcode-53:求最大子序和
 * https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * @author yixiaoshuang
 * @date 2020/7/28 11:40
 */
@Slf4j
public class MaxSubArray {

    public static int calculate(List<Integer> nums) {
        int pre = 0;
        int max = nums.get(0);
        for (int i = 0, len = nums.size(); i < len; i++) {
            int x = nums.get(i);
            pre = Math.max(pre + x, x);
            max = Math.max(max, pre);
        }

        return max;
    }

    public static void main(String[] args) {
        List<Integer> nums = Lists.newArrayList(-2, 11, 3, -4, -12, -2, -13, -5, -4);
        int max = calculate(nums);

        log.info("max:{}", max);
    }
}
