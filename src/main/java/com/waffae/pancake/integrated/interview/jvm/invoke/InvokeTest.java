package com.waffae.pancake.integrated.interview.jvm.invoke;

/**
 * @author yixiaoshuang
 * @date 2021/3/12 22:44
 */
public class InvokeTest {

    public static void main(String[] args) {
        // 调用 invokestatic指令
        m();

        // 调用了构造方法，执行了invokespecial指令
        InvokeTest test = new InvokeTest();
        // 调用私有方法，执行了 invokespecial指令
        test.n();

        // 调用了public方法，执行了 invokevirutal指令
        test.m3();
    }

    public static void m() {
    }

    private void n() {

    }

    public void m3() {
    }
}


