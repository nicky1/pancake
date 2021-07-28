package com.waffle.pancake.integrated.tech.nio.epoll;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.List;
import java.util.Set;

/**
 * 模拟epoll selector多路复用器
 *
 * @author yixiaoshuang
 * @date 2021/7/27 23:37
 */
public class NioEpollSelector {
    static List<SocketChannel> channels = Lists.newArrayList();
    static ByteBuffer byteBuffer = ByteBuffer.allocate(512);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();

        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6380);
        serverSocket.bind(socketAddress);
        serverSocket.configureBlocking(false);

        // 引入selector神器,使用selector处理channel(epoll)模式,可以收集读写的channel,最终调用的是操作系统的函数,
        // linux:epoll_create
        Selector selector = Selector.open();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            // 阻塞,要么有连接进来，要么有数据进来
            selector.select();

            // selector里会存储IO EVENTS事件;
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for (SelectionKey selectionKey : selectionKeys){
                // 连接事件
                if (selectionKey.isAcceptable()){

                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel client = server.accept();
                    if (null != client){
                        client.configureBlocking(false);

                        // 把新的客户端连接也注册到selector上
                        client.register(selector, SelectionKey.OP_READ);
                        System.out.println("connect success");

                    }
                }else if (selectionKey.isReadable()){
                    System.out.println("read");
                    // 读数据事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer1 = ByteBuffer.allocate(512);
                    int read = socketChannel.read(byteBuffer1);
                    if (read > 0){
                        System.out.println("read success:"+ new String(byteBuffer1.array()));
                    }else if (read == -1){
                        System.out.println("客户端断开");
                        socketChannel.close();
                    }

                }
            }


        }


    }

}
