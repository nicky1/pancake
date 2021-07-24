package com.waffle.pancake.integrated.tech.algorithms.array;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集
 *
 * @author yixiaoshuang
 * @date 2021/3/31 17:30
 */
public class Intersect {

    /**
     * 求数组交集：数组元素少时
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
     * 我们可以不考虑输出结果的顺序
     * <p>
     * nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 4 5 9
     * 4 4 8 9 9
     */
    public static int[] intersect(int nums1[], int nums2[]) {
        // 先将2个数组排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int len1 = nums1.length;
        int len2 = nums2.length;
        int i = 0, j = 0;

        List<Integer> list = Lists.newArrayList();

        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        // 把list转为数组
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }

    public static int[] intersect2(int nums1[], int nums2[]) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        List<Integer> list = new ArrayList<>();

        // 第一个数组转为map:k-数组元素 v-出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len1; i++) {
            int tmp = nums1[i];
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        }

        // 遍历第二个数组
        for (int i = 0; i < len2; i++) {
            int num = nums2[i];
            if (map.getOrDefault(num, 0) > 0) {
                list.add(num);
                map.put(num, map.get(num) - 1);
            }
        }

        // 把list转为数组
        int index = 0;
        int[] res = new int[list.size()];
        for (int k = list.size() - 1; k >= 0; k--) {
            res[index++] = list.get(k);
        }
        return res;
    }

    public static void main(String[] args) {
        int a[] = {4, 9, 5, 5, 4};
        int b[] = {7, 8, 4, 9, 5, 6, 4};

        int c[] = intersect2(a, b);

        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }
    }
}
