package org.nicolas.service;

import org.nicolas.controller.ChatController;
import org.nicolas.pojo.ClientPojo;
import org.nicolas.pojo.ServerPojo;
import org.nicolas.socket.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 聊天功能Service类
 *
 * @author zorth
 * @date 2021-12-08 16:49:40
 */
@Service
public class ChatService {
    private final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private final Client client;

    public ChatService(Client client) {
        this.client = client;
    }

    public void SendMessage(String nickname, String message) {
        client.sendMessage(nickname, message);
    }

    public ClientPojo Connect(String nickname, ThreadPoolExecutor globalExecutor) {
        ClientPojo pojo = client.initialize(nickname, globalExecutor);
        return pojo;
    }

    public Boolean Disconnect(ClientPojo pojo) {
        boolean severDisconnect = false;
        for (ServerPojo serverPojo : ChatController.serverPojos) {
            if (serverPojo.getNickname().equals(pojo.getNickname())) {
                severDisconnect = closeRs(serverPojo.getPrintStream(), serverPojo.getBufferedReader(), serverPojo.getSocket());
                ChatController.serverPojos.remove(serverPojo);
                break;
            }
        }
        if (severDisconnect) {
            logger.info("开始断开客户端链接...");
            return client.closeRs(pojo.getPrintStream(), pojo.getBufferedReader(), pojo.getSocket(), pojo);
        }
        logger.info("链接断开失败");
        return false;
    }


    /**
     * 关闭输入流输出流
     */
    public Boolean closeRs(PrintStream ps, BufferedReader bufferedReader, Socket socket) {
        try {
            logger.info("开始尝试断开服务...");
            if (bufferedReader != null) {
                ps.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (socket != null) {
                socket.close();
            }
            ChatController.clients.removeByValue(ps);
            logger.info("服务断开成功...");
            return true;
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

}
