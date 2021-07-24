package com.waffle.pancake.integrated.tech.algorithms.string;

/**
 * 最长公共前缀
 * <p>
 * https://leetcode-cn.com/problems/longest-common-prefix/
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @author yixiaoshuang
 * @date 2021/5/4 15:24
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String strs[] = {"afalower", "fblow", "fcloight"};
        String result = longestCommonPrefix(strs);
        System.out.println(result);
    }

    /**
     * ab,abc,fab,cfdeabc
     */
    public static String longestCommonPrefix(String[] strs) {
        // 1.先找出长度最小的字符串
        int minLen = strs[0].length();
        int i = 0;
        String minStr = strs[0];

        while (i < strs.length) {
            if (strs[i].length() < minLen) {
                minLen = strs[i].length();
                minStr = strs[i];
            }
            i++;
        }
        StringBuilder commonPrefix = new StringBuilder();
        boolean isMatch = true;
        // 2.拿最短字符串的每个字符去和其它字符串比较，有一个字符串不匹配字符则退出
        for (int j = 0; j < minStr.length(); j++) {
            char c = minStr.charAt(j);
            for (String s : strs){
                if (s.charAt(j) != c){
                    isMatch = false;
                }
            }

            if (isMatch){
                commonPrefix.append(c);
            }

        }

        return commonPrefix.toString();
    }

}
