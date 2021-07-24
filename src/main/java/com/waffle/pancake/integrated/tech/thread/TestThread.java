package com.waffle.pancake.integrated.tech.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/8/27 09:44
 * @Description:
 */
@Slf4j
public class TestThread {
    public static void main(String[] args) {

////        ThreadPoolExecutor
//        StringBuilder sb = new StringBuilder();
//        StringBuffer buffer = new StringBuffer();
//
//        String str2 = new String("hello world");
//
//        String str3 = new String("hello world");
//
//        log.info((str2 == str3)+"");
//
//        String s3 ="hello2";
//
//        //在编译期间,已经优化成 hello2
//        String s33 = "hello"+2;
//        log.info((s3 == s33)+"");
//
//        //
//        String a = "hello2";
//
//        String b ="hello";
//        String c = b +"2";
//        log.info((a == c)+"");

//        String a2 = "hello2";
//
//        String b2 ="hello";
//        String c2 = b2 +"2";
//        log.info((a2 == c2)+"");


        Thread thread = new Thread(new WT());

        thread.start();

    }


}


class WT implements Runnable {

    @Override
    public void run() {
        System.out.println("111111111");

        while (true) {
            System.out.println("2222");
        }
    }
}