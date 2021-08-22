package com.waffle.pancake.integrated.tech.j2se;

/**
 * @author yixiaoshuang
 * @date 2021/8/18 21:16
 */
public class SunB extends FatherC {



    public static void main(String[] args) {

        String s = new String("s");
        s = "a";
        System.out.println(s);

        String a = "a";
        String b = testStringAppend(a);

        System.out.println("a=" + a);
        System.out.println("b=" + b);
        System.out.println(b.hashCode());

        b = "world";

        System.out.println(b.hashCode());

        String s3 = new String("s3");
        String s4 = new String("s3");
        System.out.println(s3 == s4);


//        final SunB sunB1 = new SunB();
//        final SunB sunB = new SunB();
//        sunB = sunB1;

//        final int d = 4;
//        d = 5;


        testTryCatchFinally();

        System.out.println("");
    }

    public static String testStringAppend(String s) {
        s = "world";
        return s;
    }

    public static void testTryCatchFinally(){
        try {
            System.out.println("try");
            System.out.println(1/0);
        }catch (Exception e){
            System.out.println("catch");
            return;
        }finally {
            System.out.println("finally");
        }
    }

}
