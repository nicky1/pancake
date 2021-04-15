package com.waffae.pancake.integrated.frame.jmh;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * @author yixiaoshuang
 * @date 2021/4/15 18:52
 */
public class PS {

    static List<Integer> list = Lists.newArrayList();

    static {
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            list.add(1000000 + r.nextInt(1000000));
        }
    }

    public static void foreach() {
        list.forEach(PS::isPrime);
    }

    public static void parallel() {
        list.parallelStream().forEach(PS::isPrime);
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
