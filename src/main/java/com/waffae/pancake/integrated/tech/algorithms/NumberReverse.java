package com.waffae.pancake.integrated.tech.algorithms;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-02-17 14:19
 * @Description:整数反转
 */
@Slf4j
public class NumberReverse {

    public static void main(String[] args) {
        int x = 1240;
        int result = reverse(x);
        log.info("reslult:{}", result);
    }

    public static int reverse(int x) {
        int num = Math.abs(x);
        int result = 0;
        while (num != 0) {
            result = result * 10 + num % 10;
            num /= 10;
        }
        return (x > 0) ? result : -result;
    }
}
