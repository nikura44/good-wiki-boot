package org.nicolas.controller;

import org.nicolas.pojo.ClientPojo;
import org.nicolas.pojo.ServerPojo;
import org.nicolas.service.ChatService;
import org.nicolas.socket.CrazyitMap;
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
import java.io.PrintStream;
import java.util.*;
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
    public static List<ServerPojo> serverPojos;
    public static ThreadPoolExecutor threadPool;
    public static CrazyitMap<String, PrintStream> clients;
    /**
     * 用来标识Server线程的栈
     */
    public static Stack<String> requestStack;
    public static Map<String,Boolean> runFlag;

    private final ChatService chatService;
    private final ThreadPoolExecutorConfig executorConfig;

    public ChatController(ChatService chatService, ThreadPoolExecutorConfig executorConfig) {
        this.chatService = chatService;
        this.executorConfig = executorConfig;
    }

    @PostConstruct
    public void chatThreadStart() {
        pojoList = new ArrayList<>();
        serverPojos = new ArrayList<>();
        chatHall = new ArrayList<>();
        //使用一个 Map 对象来保存每个客户名称和对应的输出流之间的关系
        clients = new CrazyitMap<>();
        requestStack = new Stack<>();
        runFlag = new HashMap<>(20);
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
        pojoList.toString();
        clients.toString();
        threadPool.toString();
        return chatHall;
    }

    @PostMapping("/initConnect")
    @ResponseBody
    public Response connect(@RequestBody Request request) {
        Response response = new Response();
        String nickname = request.getReqMsgAuth();
        runFlag.put(nickname,true);
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

    @PostMapping("/disconnect")
    @ResponseBody
    public Response disconnect(@RequestBody Request request) {
        Response response = new Response();
        String nickname = request.getReqMsgAuth();
        runFlag.replace(nickname,false);
        for (ClientPojo pojo : pojoList) {
            if (pojo.getNickname().equals(nickname)) {
                logger.info("开始关闭链接，当前链接数： " + threadPool.getActiveCount());
                chatService.Disconnect(pojo);
                logger.info("关闭成功，当前链接数: " + threadPool.getActiveCount());
                response.setRespBody("success");
                return response;
            }
        }
        logger.error("没有找到该用户的链接，关闭失败，当前链接数为： " + threadPool.getActiveCount());
        return response;
    }
}
