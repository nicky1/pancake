package com.waffle.pancake.integrated.tech.algorithms.array;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * @author yixiaoshuang
 * @date 2021/5/3 12:42
 */
public class TwoArraySum {
    public static void main(String[] args) {
        int a[] = {2, 7, 11, 15};
        int target = 9;
        int result[] = twoSum(a, target);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public static int[] twoSum(int a[], int target) {
        Map<Integer, Integer> map = Maps.newHashMapWithExpectedSize(a.length);

        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(target - a[i])) {
                return new int[]{map.get(target - a[i]),i};
            }
            map.put(a[i], i);
        }
        return new int[0];
    }

}
