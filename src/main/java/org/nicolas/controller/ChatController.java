package org.nicolas.controller;

import org.nicolas.service.BlogService;
import org.nicolas.service.ChatService;
import org.nicolas.socket.Server;
import org.nicolas.thread.ThreadPoolExecutorConfig;
import org.nicolas.util.Request;
import org.nicolas.util.Response;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zorth
 * @date 2021-12-08 17:00:16
 */
@Controller
@CrossOrigin
public class ChatController {

    public static String chatHall;
    public static ThreadPoolExecutor threadPool;

    private final ChatService chatService;
    private final ThreadPoolExecutorConfig executorConfig;

    public ChatController(ChatService chatService, ThreadPoolExecutorConfig executorConfig) {
        this.chatService = chatService;
        this.executorConfig = executorConfig;
    }

    @PostConstruct
    public void chatThreadStart() {
        ThreadPoolExecutor threadPool = executorConfig.createThreadPool();
        ChatController.threadPool = threadPool;
        Server server = new Server(threadPool);
        server.init();
        System.out.println("init success!");


    }

    @PostMapping("/getMessage")
    public Response getMessage(@RequestBody Request request) {
        Response response = new Response();
        request.setReqBody(chatHall);
        return response;
    }

    @PostMapping("/connect")
    public Response connect() {
        Response response = new Response();
        chatService.Connect("nicolas",ChatController.threadPool);
        return response;
    }

    @PostMapping("/sendMessage")
    public Response sendMessage() {
        Response response = new Response();
        chatService.SendMessage("request.getReqMsgAuth()");
        return response;
    }

}
