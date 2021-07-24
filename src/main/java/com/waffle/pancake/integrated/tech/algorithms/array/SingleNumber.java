package com.waffle.pancake.integrated.tech.algorithms.array;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗
 * [4,1,2,1,2] 4
 *
 * @author yixiaoshuang
 * @date 2021/3/31 15:50
 */
public class SingleNumber {

    /**
     * 找出只出现一次的元素
     * 使用 ^ 异或，不熟悉位移操作
     */
    public static int singleNumber(int nums[]) {
        int len = nums.length;

        int res = 0;
        for (int i = 0; i < len; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int nums[] = {4, 1, 2, 1, 2};
        int num = singleNumber(nums);
        System.out.println(num);
    }
}
