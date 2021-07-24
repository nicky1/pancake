package com.waffle.pancake.integrated.tech.algorithms.array;

import lombok.extern.slf4j.Slf4j;

/**
 * leetcode-122:股票买卖最好时机,可以多次买卖
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
 *
 * @author yixiaoshuang
 * @date 2020/7/23 08:54
 */
@Slf4j
public class MaxProfit2 {

    /**
     * 多次买卖，利润最大化
     * 1.即保证 卖出-买入 > 0,累加即利润最大,累加 正数和即可。
     */
    public static int greedy(int nums[]) {
        int max = 0;
        for (int i = 0; i < nums.length-1; i++) {
            max += Math.max(nums[i + 1] - nums[i], 0);
        }

        return max;
    }

    public static void main(String[] args) {
        int nums[] = {0,7, 6, 5, 1, 2, 4};
        int max = greedy(nums);
        System.out.println(max);
    }

}
