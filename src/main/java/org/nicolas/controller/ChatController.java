package org.nicolas.controller;

import org.nicolas.pojo.ClientPojo;
import org.nicolas.service.ChatService;
import org.nicolas.socket.Server;
import org.nicolas.thread.ThreadPoolExecutorConfig;
import org.nicolas.util.Request;
import org.nicolas.util.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zorth
 * @date 2021-12-08 17:00:16
 */
@Controller
@CrossOrigin
public class ChatController {
    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    public static List<String> chatHall;
    public static List<ClientPojo> pojoList;
    public static ThreadPoolExecutor threadPool;

    private final ChatService chatService;
    private final ThreadPoolExecutorConfig executorConfig;

    public ChatController(ChatService chatService, ThreadPoolExecutorConfig executorConfig) {
        this.chatService = chatService;
        this.executorConfig = executorConfig;
    }

    @PostConstruct
    public void chatThreadStart() {
        pojoList = new ArrayList<>();
        chatHall = new ArrayList<>();
        ThreadPoolExecutor threadPool = executorConfig.createThreadPool();
        ChatController.threadPool = threadPool;
        Server server = new Server(threadPool);
        server.init();
        logger.info("Server init success!");
    }

    @PostMapping("/getMessage")
    @ResponseBody
    public List<String> getMessage() {
        for (String line : chatHall) {
           logger.info(line);
        }
        return chatHall;
    }

    @PostMapping("/initConnect")
    @ResponseBody
    public Response connect(@RequestBody Request request) {
        Response response = new Response();
        String nickname = request.getReqMsgAuth();
        ClientPojo pojo = chatService.Connect(nickname, ChatController.threadPool);
        pojoList.add(pojo);
        response.setRespMsgAuth(pojo.getNickname());
        response.setRespBody("success");
        return response;
    }

    @PostMapping("/sendMessage")
    @ResponseBody
    public Response sendMessage(@RequestBody Request request) {
        Response response = new Response();
        String nickname = request.getReqMsgAuth();
        String message = (String) request.getReqBody();
        chatService.SendMessage(nickname, message);
        return response;
    }

}
