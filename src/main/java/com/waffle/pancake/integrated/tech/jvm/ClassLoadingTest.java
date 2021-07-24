package com.waffle.pancake.integrated.tech.jvm;

/**
 * 测试类加载过程-初始化
 *
 * @author yixiaoshuang
 * @date 2021/3/9 20:23
 */
public class ClassLoadingTest {
    public static void main(String[] args) {
        System.out.println(T3.count);

        System.out.println(T4.count);

    }
}

class T3 {
    // 先初始化count = 2
    public static int count = 2;

    // 执行构造方法 count++
    public static T3 t = new T3();

    private T3() {
        count++;
    }
}

class T4 {

    // 先执行构造方法 count++ ,count = 2
    public static T4 t = new T4();

    // 接着直接赋值count=2
    public static int count = 2;

    private T4() {
        count++;
    }
}
