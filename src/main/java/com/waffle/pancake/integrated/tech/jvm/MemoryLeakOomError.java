package com.waffle.pancake.integrated.tech.jvm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 测试内存泄漏
 *
 *
 * @author yixiaoshuang
 * @date 2021/9/5 10:10
 */
public class MemoryLeakOomError {


    public static void main(String[] args) throws InterruptedException {
        List<OutOfMemory> list = Lists.newArrayList();
        while (true){
//            TimeUnit.MILLISECONDS.sleep(10);
            list.add(new OutOfMemory("1111"));
        }
    }

}

class OutOfMemory{
    private String test;

    public OutOfMemory(){ }

    public OutOfMemory(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }
}
