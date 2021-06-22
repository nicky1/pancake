package com.waffae.pancake.integrated.tech.algorithms.array;

/**
 * 删除有序数组中的重复元素,原地删除，不可以使用额外的数据结构。
 * 不需要考虑数组中超出新长度后面的元素 ***
 * 1.使用快慢指针的思路，快-> 从下标为1 的元素开始遍历 慢->从下标0 的元素开始遍历
 * 2.快慢指针对应的元素(即前后) 不相等,则 慢指针下标+1 同时更新值。
 *
 * @author yixiaoshuang
 * @date 2021/3/29 08:50
 */
public class RemoveDupNum {

    /**
     * 1.删除数组中的重复元素,
     * 2.并返回处理后数组的长度
     * [1,1,2,3,3,4,5,5,8]
     */
    public static int removeDuplicatesNum(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        // 慢指针
        int p = 0;

        // 快指针
        for (int i = 1; i < nums.length; i++) {
            // 不相等
            if ((nums[i] ^ nums[p]) != 0) {
                // 慢指针下标+1 且重新赋值
                nums[++p] = nums[i];
            }
        }

        return p + 1;
    }

    public static void main(String[] args) {
        int a[] = {1, 1, 2, 3, 3, 4,  8};
        int p = removeDuplicatesNum(a);
        System.out.println(p);

    }
}
