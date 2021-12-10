package org.nicolas.service;

import org.nicolas.pojo.ClientPojo;
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

    public void SendMessage(String nickname, String message) {
        client.sendMessage(nickname, message);
    }

    public ClientPojo Connect(String nickname, ThreadPoolExecutor globalExecutor) {
        ClientPojo pojo = client.initialize(nickname, globalExecutor);
        return pojo;
    }

    public Boolean Disconnect(ClientPojo pojo) {
        return client.closeRs(pojo.getPrintStream(), pojo.getBufferedReader(), pojo.getSocket(), pojo);
    }
}
