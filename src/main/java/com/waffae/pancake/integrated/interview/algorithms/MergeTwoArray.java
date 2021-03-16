package com.waffae.pancake.integrated.interview.algorithms;

/**
 * Leetcode88-合并2个有序数组
 *
 * @author yixiaoshuang
 * @date 2020/7/28 21:37
 */
public class MergeTwoArray {

    /**
     * 从nums1数组的(m+n-1)个位置开始赋值，从后往前
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;
        int p1 = m - 1;
        int p2 = n - 1;
        while (p1 >= 0 && p2 >= 0) {
            int k = p--;
            if (nums1[p1] > nums2[p2]) {
                nums1[k] = nums1[p1];
                p1--;
            } else {
                nums1[k] = nums2[p2];
                p2--;
            }
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args) {
        int nums1[] = {1, 2, 3, 0, 0, 0};
        int nums2[] = {2, 5, 6};

        merge(nums1, 3, nums2, nums2.length);

        for (int n : nums1) {
            System.out.println(n);
        }

    }
}
