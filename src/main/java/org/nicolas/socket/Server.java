package org.nicolas.socket;

import org.nicolas.controller.ChatController;
import org.nicolas.pojo.ClientPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zorth
 */
public class Server {


    private ThreadPoolExecutor executor;

    public Server(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    private static ServerSocket ss = null;

    /**
     * 服务端口号
     */
    private static final int SERVER_PORT = 30000;


    private class ServerMainThread implements Runnable {

        @Override
        public void run() {
            Thread.currentThread().setName("Server--ServerMainThread");
            // 使用无限循环不断接收来自客户端的请求
            while (true) {
                try {
                    Socket socket = Server.ss.accept();

                    executor.execute(new ServerThread(socket));

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * initialize of Server
     */
    public void init() {
        try {
            // 建立监听ServerSocket
            ss = new ServerSocket(SERVER_PORT);
            executor.execute(new ServerMainThread());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
