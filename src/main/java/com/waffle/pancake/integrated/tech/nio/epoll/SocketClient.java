package com.waffle.pancake.integrated.tech.nio.epoll;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author yixiaoshuang
 * @date 2021/7/6 14:21
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 6380);
        String str = new Date() + " hello world";
        client.getOutputStream().write(str.getBytes());

    }
}
