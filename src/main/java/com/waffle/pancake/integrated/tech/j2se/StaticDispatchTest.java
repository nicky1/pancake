package com.waffle.pancake.integrated.tech.j2se;

/**
 * 静态分派-方法重载
 *
 * @author yixiaoshuang
 * @date 2020/12/16 09:50
 */
public class StaticDispatchTest {

    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayHello(Human human) {
        System.out.println("hello gay");
    }

    public void sayHello(Man man) {
        System.out.println("hello man");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello woman");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatchTest constrTest = new StaticDispatchTest();

        // 都会输出 say hello,因为在编译器就确定了要调用重载的那个方法，属于静态分派。
        constrTest.sayHello(man);
        constrTest.sayHello(woman);

        Man man2 = new Man();
        constrTest.sayHello(man2);
    }
}
