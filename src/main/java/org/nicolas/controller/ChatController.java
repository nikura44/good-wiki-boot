package org.nicolas.controller;

import org.nicolas.service.BlogService;
import org.nicolas.service.ChatService;
import org.nicolas.socket.Server;
import org.nicolas.thread.ThreadPoolExecutorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zorth
 * @date 2021-12-08 17:00:16
 */
@Controller
@CrossOrigin
public class ChatController {
    private final ChatService chatService;
    private final ThreadPoolExecutorConfig executorConfig;

    public ChatController(ChatService chatService, ThreadPoolExecutorConfig executorConfig) {
        this.chatService = chatService;
        this.executorConfig = executorConfig;
    }

    @PostConstruct
    public void chatThreadStart() {
        ThreadPoolExecutor threadPool = executorConfig.createThreadPool();
        Server server = new Server(threadPool);
        server.init();
    }
}
