package com.waffae.pancake.integrated.tech.algorithms.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author yixiaoshuang
 * @date 2021/5/4 16:52
 */
public class ValidParentheses {

    public static void main(String[] args) {
        String s = "([]]";
        boolean result = isValid(s);
        System.out.println(result);

    }

    /**
     * 使用栈的数据结构，这块还不熟悉，需要加强。
     * 如果遇到 左括号，放到栈顶，遇到匹配的右括号，则出栈。
     */
    public static boolean isValid(String s) {
        int len = s.length();
        // 如果字符串的长度是奇数，则返回false
        if (len % 2 != 0) {
            return false;
        }

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (getSwitchChar(c) != ' ') {
                if (stack.isEmpty() || stack.peek() != getSwitchChar(c)){
                    return false;
                }
                stack.pop();
            }else{
                stack.push(c);
            }
        }
        // 栈为空则说明字符全部匹配成功
        return stack.isEmpty();
    }

    public static Character getSwitchChar(char c) {
        switch (c) {
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';

        }
        return ' ';
    }

}
