package com.waffle.pancake.integrated.tech.algo.stack;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yixiaoshuang
 * @Date: 2019-03-24 15:12
 * @Description:数组实现栈
 */
@Slf4j
public class ArrayStack {

    private String itmes[];
    private int count;
    private int n;

    public ArrayStack(int n) {
        this.itmes = new String[n];
        this.count = 0;
        this.n = n;
    }

    public boolean push(String value) {
        if (n == count) {
            return false;
        }
        itmes[count] = value;
        count++;
        return true;
    }

    public String pop() {
        if (count == 0) {
            return StringUtils.EMPTY;
        }
        String value = itmes[count - 1];
        count--;
        return value;
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);

        stack.push("111");
        stack.push("222");
        stack.push("333");
        stack.push("444");
        stack.push("555");

        for (int i = 0; i < 10; i++) {
            log.info(stack.pop());
        }

    }
}
