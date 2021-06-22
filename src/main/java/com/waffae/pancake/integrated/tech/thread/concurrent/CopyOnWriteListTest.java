package com.waffae.pancake.integrated.tech.thread.concurrent;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 测试并发安全的ArrayList: CopyOnWriteArrayList
 *
 * @author yixiaoshuang
 * @date 2020/12/2 08:46
 */
public class CopyOnWriteListTest {

    public static void main(String[] args) {
        // 线程不安全
        List<String> list = new ArrayList();
//        for (int i = 1; i <= 100; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    list.add(UUID.randomUUID().toString().substring(0, 5));
//                    // 这行代码不能删除,否则演示不出ConcurrentModificationException
//                    System.out.println(list);
//                }
//            }, String.valueOf(i)).start();
//        }

        // 使用线程安全的CopyOnWriteArrayList:使用了ReentrantLock锁，
        List<String> list2 = Lists.newCopyOnWriteArrayList();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list2.add(UUID.randomUUID().toString().substring(0, 7));
                System.out.println(list2);
            }).start();
        }
    }

}
