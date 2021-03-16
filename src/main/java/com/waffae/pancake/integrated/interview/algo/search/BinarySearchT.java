package com.waffae.pancake.integrated.interview.algo.search;

/**
 * 查找算法-二分查找
 * 使用场景
 * 1.二分插在针对是一个有序的数据集合
 * 2.数组的数据结构
 * 3.大数据量适合二分查找
 *
 * @author yixiaoshuang
 * @date 2020/10/22 22:25
 */
public class BinarySearchT {

    /**
     * 查找指定元素在数组中的下标
     */
    public static int bsearch(int a[], int n, int value) {
        int high = n - 1;
        int low = 0;
        int step = 0;
        while (low <= high) {
            step++;
            System.out.println("一共循环了 " + step + "次");
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找数组中第一个等于给定值的下标
     */
    public static int firstBsearch(int a[], int n, int value) {
        int high = n - 1;
        int low = 0;
        int step = 0;
        while (low <= high) {
            step++;
            System.out.println("一共循环了 " + step + "次");
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                // 这时要去判断是否是第一次出现
                if (mid == 0 || (a[mid - 1] != value)) {
                    return mid;
                }
                high = mid - 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找数组中最后一个等于给定值的下标
     */
    public static int lastBsearch(int a[], int n, int value) {
        int high = n - 1;
        int low = 0;
        int step = 0;
        while (low <= high) {
            step++;
            System.out.println("一共循环了 " + step + "次");
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                // 这时要去判断是否是最后一次出现
                if (mid == n - 1 || (a[mid + 1] != value)) {
                    return mid;
                }
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找数组中第一个大于或等于给定值的下标
     */
    public static int firstGtBsearch(int a[], int n, int value) {
        int high = n - 1;
        int low = 0;
        int step = 0;
        while (low <= high) {
            step++;
            System.out.println("一共循环了 " + step + "次");
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if (mid == 0 || (a[mid - 1] < value)) {
                    return mid ;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找数组中最后一个小于或等于给定值的下标
     */
    public static int lastLtBsearch(int a[], int n, int value) {
        int high = n - 1;
        int low = 0;
        int step = 0;
        while (low <= high) {
            step++;
            System.out.println("一共循环了 " + step + "次");
            int mid = low + ((high - low) >> 1);
            if (a[mid] <= value) {
                if (mid == n-1 || (a[mid + 1] > value)) {
                    return mid ;
                }
                low = mid + 1;
            } else {
                high=mid - 1;
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 5, 9, 22, 22, 33, 34, 34, 43, 67};
        int index = lastLtBsearch(a, a.length, 32);
        System.out.println(index);
    }
}
