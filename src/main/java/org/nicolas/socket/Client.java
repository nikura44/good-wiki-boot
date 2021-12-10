package org.nicolas.socket;

import org.nicolas.controller.ChatController;
import org.nicolas.entry.Response;
import org.nicolas.pojo.ClientPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zorth
 */
@Component
public class Client {
    private final Logger logger = LoggerFactory.getLogger(Client.class);
    /**
     * 服务端端口号
     */
    public static final int SERVER_PORT = 30000;

    /**
     * 服务端ip地址
     */
    public static final String SERVER_IP = "127.0.0.1";

    /**
     * 私聊标记符
     */
    private static final String PRIVATE_MESSAGE_MARKER = "//";

    /**
     * 对话标记符
     */
    private static final String TALK_MARKER = ":";


    /**
     * Client初始化方法：链接到服务段
     *
     * @return
     */
    public ClientPojo initialize(String nickname, ThreadPoolExecutor globalExecutor) {
        logger.info("开始执行Client初始化方法...");
        logger.info("当前线程池状态： " + globalExecutor.getActiveCount());
        try {
            //连接到服务器
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            //获取该Socket对应到输入流和输出流
            PrintStream ps = new PrintStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //开始执行nickname校验
            ps.println(CrazyitProtocol.USER_ROUND + nickname + CrazyitProtocol.USER_ROUND);

            //读取服务器的相应
            String result = bufferedReader.readLine();
            if (result.equals(CrazyitProtocol.NAME_REP)) {
                logger.error("用户名重复！请重新输入");
                // TODO: 2021/12/10  怎么才能重新输入
            }
            //如果服务器返回登陆成功，则结束循环
            if (result.equals(CrazyitProtocol.LOGIN_SUCCESS)) {
                logger.info("nickname is checked");
            }
            // 用该Socket输入流启动ClientThread线程
            globalExecutor.execute(new ClientThread(bufferedReader, nickname));
            logger.info("Client初始化成功");
            logger.info("当前线程池状态： " + globalExecutor.getActiveCount());
            ClientPojo pojo = new ClientPojo(nickname, socket, bufferedReader, ps, true);
            return pojo;
        } catch (UnknownHostException exception) {
            logger.error("找不到远程服务器");
//            closeRs();
            //TODO 关闭链接的方法
        } catch (IOException e) {
            logger.error("网络异常");
//            closeRs();
            //TODO 关闭链接的方法
        }

        return null;
    }


    /**
     * 定义向网络发送message的方法
     */
    public Response sendMessage(String nickname, String message) {
        Response response = new Response();
        for (ClientPojo pojo : ChatController.pojoList) {
            if (pojo.getNickname().equals(nickname)) {
                PrintStream ps = pojo.getPrintStream();
                //如果发送的信息中有冒号，并以//开头，则认为是私聊
                if (message.indexOf(TALK_MARKER) > 0 && message.startsWith(PRIVATE_MESSAGE_MARKER)) {
                    message = message.substring(2);
                    ps.println(CrazyitProtocol.PRIVATE_ROUND + message.split(":")[0] + CrazyitProtocol.SPLIT_SIGN
                            + message.split(":")[1] + CrazyitProtocol.PRIVATE_ROUND);
                } else {
                    ps.println(CrazyitProtocol.MSG_ROUND + message + CrazyitProtocol.MSG_ROUND);
                }
                response.setRespMsg("success");
                return response;
            }

        }
        response.setRespMsg("error");
        return response;
    }

    /**
     * 关闭输入流输出流
     */
    public Boolean closeRs(PrintStream ps, BufferedReader bufferedReader, Socket socket, ClientPojo pojo) {
        try {
            logger.info("开始尝试断开链接...");
            pojo.setStatus(false);
            if (bufferedReader != null) {
                ps.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (socket != null) {
                socket.close();
            }
            logger.info("delete connect success");
            return true;
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return false;
    }


}

