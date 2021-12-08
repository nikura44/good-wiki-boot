package org.nicolas.socket;

import org.nicolas.entry.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author zorth
 */
public class Client {
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

    private Socket socket;

    private PrintStream ps;

    /**
     * 输入流
     */
    private BufferedReader bufferedReader;

    /**
     * 用户昵称
     */
    private String nikename;

    /**
     * Client初始化方法：链接到服务段
     * @return
     */
    public Response initialize() {
        Response response = new Response();
        try {
            //连接到服务器
            socket = new Socket(SERVER_IP, SERVER_PORT);
            //获取该Socket对应到输入流和输出流
            ps = new PrintStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException exception) {
            System.out.println("找不到远程服务器");
            response.setRespMsg("找不到远程服务器");
            closeRs();
            return response;
        } catch (IOException e) {
            System.out.println("网络异常");
            response.setRespMsg("网络异常");
            closeRs();
            return response;
        }
        response.setRespMsg("success");
        return response;
    }

    /**
     * 检查nickname字段是否重复
     * @param nickname
     * @return Response
     */
    public Response checkNickname(String nickname) {
        Response response = new Response();
        ps.println(CrazyitProtocol.USER_ROUND + nickname + CrazyitProtocol.USER_ROUND);
        try {
            //读取服务器的相应
            String result = bufferedReader.readLine();
            if (result.equals(CrazyitProtocol.NAME_REP)) {
                response.setRespMsg("用户名重复！请重新输入");
                return response;
            }
            //如果服务器返回登陆成功，则结束循环
            if (result.equals(CrazyitProtocol.LOGIN_SUCCESS)) {
                response.setRespMsg("success");
                return response;
            }
        } catch (IOException e) {
            response.setRespMsg(e.getMessage());
            return response;
        }
        return response;
    }

    /**
     * 定义向网络发送message的方法
     */
    public Response sendMessage(String message) {
        Response response = new Response();
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

    /**
     * 关闭输入流输出流
     */
    public Response closeRs() {
        Response response = new Response();
        try {
            System.out.println("关闭链接");
            if (bufferedReader != null) {
                ps.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (socket != null) {
                socket.close();
            }
            response.setRespMsg("success");
        } catch (IOException e) {
            response.setRespMsg(e.getMessage());
            return response;
        }
        return response;
    }

//    public static void main(String[] args) {
//        var client = new Client();
//        client.init();
//        client.readAndSend();
//    }
}

