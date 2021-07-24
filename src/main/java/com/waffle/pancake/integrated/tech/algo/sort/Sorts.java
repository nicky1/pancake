package com.waffle.pancake.integrated.tech.algo.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-03-27 23:25
 * @Description:插入排序
 */
@Slf4j
public class Sorts {

    /**
     * @desc：1.冒泡排序，相邻2个数比较，移动位置 2.多趟遍历，每趟排序
     **/
    public static void bubble(int array[]) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            //跳出循环的标志，避免无用的循环
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }

    }

    /**
     * @描述：插入排序 从第二个元素开始，拿出来和前面的元素排序。保证左边始终是有序的。
     **/
    public static void insertSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; ++i) {
            int value = arr[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (arr[j] > value) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = value;
        }

    }

    /**
     * @desc：选择排序 1.找出最小值
     **/
    public static void selectSort(int a[]) {


    }

    public static int divide(int[] a, int start, int end) {
        //每次都以最右边的元素作为基准值
        int base = a[end];
        //start一旦等于end，就说明左右两个指针合并到了同一位置，可以结束此轮循环。
        while (start < end) {
            while (start < end && a[start] <= base) {
                //从左边开始遍历，如果比基准值小，就继续向右走
                start++;
            }

            //上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
            if (start < end) {
                //交换
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值右边)，因此右边也要同时向前移动一位
                end--;
            }
            while (start < end && a[end] >= base) {
                //从右边开始遍历，如果比基准值大，就继续向左走
                end--;
            }

            //上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换
            if (start < end) {
                //交换
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
                start++;
            }

        }
        //这里返回start或者end皆可，此时的start和end都为基准值所在的位置
        return end;

    }

    /**
     * @desc：快速排序 1.使用递归
     * 2.一般使用最右
     **/
    public static void quickSort(int a[], int start, int end) {
        if (start >= end) {
            return;
        }
        int p = divide(a, start, end);
        quickSort(a, start, p - 1);
        quickSort(a, p + 1, end);
    }

    /**
     * @描述：
     **/
    public static void main(String[] args) {
//        int ar[] = new int[]{2, 23, 1, 89, 2, 1, 33, 44, 9};
        int ar[] = new int[]{19, 5, 4, 6, 1, 3, 2};
        log.info(Arrays.toString(ar));
//        bubble(ar);
        insertSort(ar);
//        log.info(Arrays.toString(ar));

//        quickSort(ar, 0, ar.length - 1);
        log.info(Arrays.toString(ar));


    }

}
