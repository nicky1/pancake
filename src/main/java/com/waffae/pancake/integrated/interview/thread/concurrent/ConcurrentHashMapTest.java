package com.waffae.pancake.integrated.interview.thread.concurrent;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {



    public static void main(String[] args) {

        HashMap hashMap = new HashMap();
        hashMap.put("a",1);
        hashMap.put("ff",2);
        hashMap.put("ff",3);

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    }



}
