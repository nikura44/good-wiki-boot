package org.nicolas.socket;

import org.nicolas.controller.ChatController;
import org.nicolas.pojo.ChatHall;
import org.nicolas.pojo.ClientPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author zorth
 */
public class ClientThread extends Thread {
    private final Logger logger = LoggerFactory.getLogger(ClientThread.class);
    /**
     * 该客户端线程负责处理输入流
     */
    BufferedReader br = null;

    String nickname;

    /**
     * 使用一个网络输入流来创建客户端线程
     */
    public ClientThread(BufferedReader br, String nickname) {
        this.br = br;
        this.nickname = nickname;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().setName("Client-" + nickname + "-Thread");
            String line = null;
            //从输入流读取数据
            while (true) {
                if ((line = br.readLine()) != null && ChatController.runFlag.get(nickname)) {
                    logger.info(line);
                    synchronized (this) {
                        logger.info(line);
                        ChatController.chatHall.add(line);
                        continue;
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

