package com.waffae.pancake.integrated.interview.jvm;

/**
 * @author yixiaoshuang
 * @date 2021/3/30 19:21
 */
public class T2 {

    String str = "";

    public T2(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append("helloworld:"+i);
        }
        str = sb.toString();
    }

}
