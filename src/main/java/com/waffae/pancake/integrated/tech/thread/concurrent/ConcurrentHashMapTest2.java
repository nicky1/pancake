package com.waffae.pancake.integrated.tech.thread.concurrent;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.UUID;

@Slf4j
public class ConcurrentHashMapTest2 {

    public static void main(String[] args) {
        HashMap<String, String> map = Maps.newHashMap();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(UUID.randomUUID().toString().substring(0, 5), "hello");
                System.out.println(map);
            }).start();
        }
    }


}
