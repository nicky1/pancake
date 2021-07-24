package com.waffle.pancake.integrated.tech.algorithms.array;

import lombok.extern.slf4j.Slf4j;

/**
 * leetcode-121:股票买卖最好时机
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/121-mai-mai-gu-piao-de-zui-jia-shi-ji-by-leetcode-/
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * @author yixiaoshuang
 * @date 2020/7/23 08:54
 */
@Slf4j
public class MaxProfit {

    /**
     * 计算最大收益=最大卖出 - 最小买入
     * 1.先得到买入的价格：一定是最小的 minPrice
     * 2.再计算利润= prices[i] - minPrice
     * 3.遍历价格数组一遍，记录minPrice;同时 每天去判断下 利润是不是最大？当遍历完所有天数，就能得到最大的利润。
     */
    public static int maxProfit(int prices[]) {
        if (prices.length <= 1) {
            return 0;
        }

        if (prices.length == 2) {
            return Math.max(prices[1] - prices[0], 0);
        }

        // 初始化使用Integer最大值作为价格的最大值,很巧妙的设计
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0, len = prices.length; i < len; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                if (prices[i] - minPrice > maxProfit) {
                    maxProfit = prices[i] - minPrice;
                }
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int prices[] = new int[]{7, 1, 5, 3, 6, 4};
        int profit = maxProfit(prices);
        log.info("maxProfit:{}", profit);
    }

}
