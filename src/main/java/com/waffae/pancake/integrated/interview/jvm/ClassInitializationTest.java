package com.waffae.pancake.integrated.interview.jvm;

/**
 * 测试类初始化执行顺序
 * 父类静态代码块----子类静态代码块------父类代码块------父类构造方法------子类代码块------子类构造方法
 */
public class ClassInitializationTest {

    public static void main(String[] args) {
        // 子类访问父类的静态变量,不会导致子类初始化
//        System.out.println(Child.age);

        // 测试创建子类对象的初始化顺序
//        Child child = new Child();

        // 访问父类的静态常量,不会导致父类初始化,因为final修饰的变量存储到常量池中
//        System.out.println(Child.fname); //只会输出 123

        // 通过数组定义引用类,不会触发此类的初始化
//        Child[] ps = new Child[10];

        System.out.println(TT.getCount2());
//        TT.count2 = 200;

//        System.out.println(Parent.age);
    }

}

class TT {
    public static int count2 = 2;

    public TT() {
        System.out.println("1111");
        System.out.println(count2);
    }

    public static int getCount2() {
        count2 = count2++;
        return count2;
    }
}

class Parent {
    public String someVariable = "Super variable";
    public static int age = 12;
    static final String fname = "123";

    static {
        System.out.println("static block of Super class is initialized");

    }

    {
        System.out.println("non static parent init");
        System.out.println();
    }

    public Parent() {
        System.out.println("init parent constructor");
    }
}

class NotUsed {
    static {
        System.out.println("notuser class is initialized");
    }
}

class Child extends Parent {
    public String someVariable = "child variable";

    static {
        System.out.println("static child class is initialized");
    }

    {
        System.out.println("non static blocks child is initialized");
    }


    public Child() {
        System.out.println("init child constructor");
    }
}
