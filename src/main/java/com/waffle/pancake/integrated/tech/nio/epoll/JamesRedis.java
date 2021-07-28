package com.waffle.pancake.integrated.tech.nio.epoll;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * 模拟测试使用JDK NIO API
 * 模拟单线程处理多路复用。类似于linux select函数
 *
 * @author yixiaoshuang
 * @date 2021/7/27 20:24
 */
public class JamesRedis {
    static List<SocketChannel> channels = Lists.newArrayList();
    static ByteBuffer byteBuffer = ByteBuffer.allocate(2048);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6380);
        serverSocket.bind(socketAddress);

        serverSocket.configureBlocking(false);

        while (true) {
            // 当连接数很大的情况下,有很多连接根本没有数据，会造成很多空循环
            for (SocketChannel channel : channels) {
                // 这里变成非阻塞
                int read = channel.read(byteBuffer);
                if (read > 0) {
                    System.out.println("read ---------");
                    byteBuffer.flip();
                    byte[] bytes = new byte[read];
                    System.out.println(new String(bytes));

                    byteBuffer.flip();
                }
            }

            // 设置了configBlocking(false),这里变成非阻塞
            SocketChannel client = serverSocket.accept();
            if (client != null) {
                System.out.println("client success");
                client.configureBlocking(false);
                channels.add(client);
                System.out.println("clients size:" + channels.size());
            }
        }

    }
}
