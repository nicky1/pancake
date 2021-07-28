package com.waffle.pancake.integrated.tech.nio.epoll;

import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author yixiaoshuang
 * @date 2021/7/6 14:21
 */
public class MuliClient {

    public static void main(String[] args) {
        // 模拟一个连接：连接到server之后,每隔2秒，向服务端发送一段文本
        new Thread(() -> {
            try {
                Socket client = new Socket("127.0.0.1", 6380);
                while (true) {
                    String str = new Date() + " hello world1111";
                    client.getOutputStream().write(str.getBytes());
                    TimeUnit.SECONDS.sleep(20);
                }
            } catch (Exception e) {

            }

        }).start();


        new Thread(() -> {
            try {
                Socket client = new Socket("127.0.0.1", 6380);
                while (true) {
                    String str = new Date() + " hello world2222";
                    client.getOutputStream().write(str.getBytes());
                    TimeUnit.SECONDS.sleep(20);
                }
            } catch (Exception e) {

            }

        }).start();

        new Thread(() -> {
            try {
                Socket client = new Socket("127.0.0.1", 6380);
                while (true) {
                    String str = new Date() + " hello world333333";
                    client.getOutputStream().write(str.getBytes());
                    TimeUnit.SECONDS.sleep(20);
                }
            } catch (Exception e) {

            }

        }).start();
    }
}
