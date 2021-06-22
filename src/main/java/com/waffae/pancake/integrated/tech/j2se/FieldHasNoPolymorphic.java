package com.waffae.pancake.integrated.tech.j2se;

/**
 * 字段不参与多态
 * 很经典的面试题
 *
 * @author yixiaoshuang
 * @date 2020/12/16 11:33
 */
public class FieldHasNoPolymorphic {

    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMoney();
        }

        public void showMoney() {
            System.out.println("i am father,money:" + money);
        }
    }


    static class Son extends Father {
        int money = 3;

        public Son() {
            money = 4;
            showMoney();
        }

        @Override
        public void showMoney() {
            System.out.println("i am son,money:" + money);
        }
    }

    public static void main(String[] args) {
        // 1.会先去调用 父类Father的构造函数,
        // 而父类的构造函数中对showMoney的方法调用是一次虚调用:实际执行的是新创建Son对象的重写的方法。此时Son类的构造函数还未执行,所以money=0
        // 2.父类构造函数执行完成后，执行子类Son的构造函数，money = 4，这里都能理解。
        Father son = new Son();

        // 3.在静态编译阶段,就已经确定 son.money访问的是父类Father的属性。
        int money = son.money;
        System.out.println("this gay has money:" + son.money);

    }
}
