package com.waffle.pancake.integrated.tech.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 判定字符是否唯一
 *
 * @author yixiaoshuang
 * @date 2021/3/23 08:53
 */
public class UniqueString {

    /**
     * 使用额外的数据结构Map存储出现的次数,会消耗内存
     */
    public static boolean isUnique(String str) {
        char[] chars = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, len = chars.length; i < len; i++) {
            char c = chars[i];

            Integer num = map.get(c);
            if (Objects.isNull(num)) {
                num = 1;
            } else {
                num = num + 1;
            }

            map.put(c, num);
            if (Objects.nonNull(num) && num > 1) {
                return false;
            }
        }

        return true;
    }

    public static boolean isUnique2(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (str.indexOf(chars[i], i + 1) != -1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        boolean result = isUnique2(s);
        System.out.println(result);
    }

}
