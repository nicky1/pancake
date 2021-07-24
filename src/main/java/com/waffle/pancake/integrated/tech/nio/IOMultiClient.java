package com.waffle.pancake.integrated.tech.nio;

import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 模拟多个连接
 *
 * @author yixiaoshuang
 * @date 2021/7/6 14:21
 */
public class IOMultiClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket client = new Socket("127.0.0.1", 8000);
                while (true) {
                    String str = new Date() + " hello world 0";
                    client.getOutputStream().write(str.getBytes());
                    TimeUnit.SECONDS.sleep(20);
                }
            } catch (Exception e) {

            }

        }).start();

        new Thread(() -> {
            try {
                Socket client = new Socket("127.0.0.1", 8000);
                while (true) {
                    String str = new Date() + " hello world 1";
                    client.getOutputStream().write(str.getBytes());
                    TimeUnit.SECONDS.sleep(20);
                }
            } catch (Exception e) {

            }

        }).start();


        new Thread(() -> {
            try {
                Socket client = new Socket("127.0.0.1", 8000);
                while (true) {
                    String str = new Date() + " hello world 2";
                    client.getOutputStream().write(str.getBytes());
                    TimeUnit.SECONDS.sleep(20);
                }
            } catch (Exception e) {

            }

        }).start();


        new Thread(() -> {
            try {
                Socket client = new Socket("127.0.0.1", 8000);
                while (true) {
                    String str = new Date() + " hello world 3";
                    client.getOutputStream().write(str.getBytes());
                    TimeUnit.SECONDS.sleep(20);
                }
            } catch (Exception e) {

            }

        }).start();


        new Thread(() -> {
            try {
                Socket client = new Socket("127.0.0.1", 8000);
                while (true) {
                    String str = new Date() + " hello world 4";
                    client.getOutputStream().write(str.getBytes());
                    TimeUnit.SECONDS.sleep(20);
                }
            } catch (Exception e) {

            }

        }).start();
    }
}
