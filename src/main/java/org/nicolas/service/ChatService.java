package org.nicolas.service;

import org.nicolas.socket.Client;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 聊天功能Service类
 *
 * @author zorth
 * @date 2021-12-08 16:49:40
 */
@Service
public class ChatService {

    private final Client client;

    public ChatService(Client client) {
        this.client = client;
    }

    public void SendMessage(String message) {
        client.sendMessage(message);
    }

    public void Connect(String nickname, ThreadPoolExecutor globalExecutor) {
        client.initialize();
        client.checkNickname(nickname);
        client.startClientThread(globalExecutor);
    }
}
