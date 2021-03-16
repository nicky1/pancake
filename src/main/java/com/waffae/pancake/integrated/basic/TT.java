package com.waffae.pancake.integrated.basic;


import com.waffae.pancake.model.User;

/**
 * @author yixiaoshuang
 * @date 2021/2/28 18:34
 */
public class TT {

    public static void main(String[] args) {
        // 通过jclasslib插件,查看user对象的加载初始化过程,可能会发生指令重排序
        User user = new User();
    }

}
