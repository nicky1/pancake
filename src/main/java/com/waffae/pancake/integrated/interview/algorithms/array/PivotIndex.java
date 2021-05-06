package com.waffae.pancake.integrated.interview.algorithms.array;

/**
 * 数组求中心点
 * https://leetcode-cn.com/leetbook/read/array-and-string/yf47s/
 * 给你一个整数数组 nums，请编写一个能够返回数组 “中心下标” 的方法。
 * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心下标，返回 -1 。如果数组有多个中心下标，应该返回最靠近左边的那一个。
 * 注意：中心下标可能出现在数组的两端。
 *
 * @author yixiaoshuang
 * @date 2021/5/6 22:32
 */
public class PivotIndex {

    public static void main(String[] args) {
        int nums[] = {1, 7, 3, 6, 5, 6};
        int index = pivotIndex2(nums);
        System.out.println(index);
    }

    public static int pivotIndex(int[] nums) {
        int sum = 0, end = 0, flag = 0;

        // 1.计算数组元素的累加和
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum - nums[0] == 0) {
            return 0;
        }

        // flag变量用来存储当前遍历的累积和，end为当前索引前的累加和(即当前索引左边的值)，即 end== sum-flag(为索引右边的值)。
        for (int i = 0; i < nums.length; i++) {
            flag += nums[i];
            if (i != 0) {
                end = flag - nums[i];
            }
            if (end == sum - flag) {
                return i;
            }
        }
        return -1;
    }

    public static int pivotIndex2(int[] nums) {
        int leftSum = 0, rightSum = 0;
        for (int i = 1; i < nums.length; i++) {
            rightSum += nums[i];
        }
        if (leftSum == rightSum) {
            return 0;
        }

        // rightSum减去左边的，如果和leftsum相等即返回index
        for (int j = 1; j < nums.length; j++) {
            rightSum-=nums[j];
            leftSum+=nums[j-1];
            if (rightSum == leftSum){
                return j;
            }
        }

        return -1;
    }

}
