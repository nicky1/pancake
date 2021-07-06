package com.waffae.pancake.integrated.tech.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统的IO编程-demo
 * 1.server端创建一个serverSocket,监听8000端口
 * 2.然后创建一个线程，线程里不断调用阻塞方法获取新的请求连接，
 * 3.当获取到连接后，给每个连接创建一个新的线程，负责从连接中读取数据
 * 4.然后读取数据是以字节流的形式。
 * <p>
 * 问题:
 * 1.客户端连接过多的场景下,server端需要支撑成千上万的连接，即会有成千上万个线程。
 * * 线程资源受限，线程是操作系统中非常宝贵的资源;同一时刻有大量的线程处于阻塞状态，非常浪费资源。
 * * 线程切换，效率低下。操作系统频繁进行线程切换，应用性能急剧下降。
 * 2.NIO是如何解决以上问题的？
 *
 * @author yixiaoshuang
 * @date 2021/7/6 14:09
 */
public class IOServer {

    public static void main(String[] args) throws Exception {

        // 创建serverSocket,监听8000端口
        ServerSocket socket = new ServerSocket(8000);

        // 1.一个线程接收连接请求
        new Thread(() -> {
            while (true) {
                try {
                    // 1.1 阻塞方法获取新的连接
                    Socket accept = socket.accept();

                    // 1.2 每一个新的连接请求进来，都创建一个线程，负责读取数据
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = accept.getInputStream();
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }

                        } catch (IOException e) {

                        }

                    }, "process data").start();


                } catch (Exception e) {

                }
            }
        }, "accept").start();


    }
}
