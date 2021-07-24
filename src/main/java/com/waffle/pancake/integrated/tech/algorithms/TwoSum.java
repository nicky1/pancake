package com.waffle.pancake.integrated.tech.algorithms;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 两数之和
 *
 * @author yixiaoshuang
 * @date 2021/3/22 16:49
 */
public class TwoSum {

    /**
     * 通过Map哈希结构存储 数组中的 值->下标。
     */
    public static int[] sum(int a[], int target) {
        Map<Integer, Integer> map = Maps.newHashMap();

        for (int i = 0, len = a.length; i < len; i++) {
            if (map.containsKey(target - a[i])) {
                return new int[]{map.get(target - a[i]), i};
            }
            map.put(a[i], i);
        }

        return null;
    }

    public static void main(String[] args) {
        int a[] = new int[]{2, 8, 1, 4};
        int target = 5;
        int r[] = sum(a, target);
        System.out.println(r);
    }
}
