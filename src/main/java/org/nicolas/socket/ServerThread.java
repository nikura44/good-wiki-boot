package org.nicolas.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author zorth
 */
public class ServerThread implements Runnable {
    private Socket socket;
    BufferedReader br = null;
    PrintStream ps = null;

    /**
     * 定义一个构造器，用于接收一个Socket来创建ServerThread线程
     */
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //获取该socket对应的输入流
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //获取Socket对应的输出流
            ps = new PrintStream(socket.getOutputStream());
            String line = null;
            while ((line = br.readLine()) != null) {
                //如果读到的行以USER_ROUND开始，并以其结束
                //则可以确定读到的是用户登陆的用户名
                if (line.startsWith(CrazyitProtocol.USER_ROUND) && line.endsWith(CrazyitProtocol.USER_ROUND)) {
                    //得到真实消息
                    String userName = getRelMsg(line);
                    if (Server.clients.map.containsKey(userName)) {
                        System.out.println("重复");
                        ps.println(CrazyitProtocol.NAME_REP);
                    } else {
                        System.out.println("成功");
                        ps.println(CrazyitProtocol.LOGIN_SUCCESS);
                        Server.clients.put(userName, ps);
                    }
                }
                //如果读到的行以PRIVATE_ROUND开始，为私聊消息
                else if (line.startsWith(CrazyitProtocol.PRIVATE_ROUND) && line.endsWith(CrazyitProtocol.PRIVATE_ROUND)) {
                    String userAndMsg = getRelMsg(line);
                    String user = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[0];
                    String msg = userAndMsg.split(CrazyitProtocol.SPLIT_SIGN)[1];

                    Server.clients.map.get(user).println(Server.clients.getKeyByValue(ps) + "悄悄对你说: " + msg);
                } else {
                    String msg = getRelMsg(line);
                    for (PrintStream clientPs : Server.clients.valueSet()) {
                        clientPs.println(Server.clients.getKeyByValue(ps) + "说： " + msg);
                    }
                }
            }
        } catch (IOException e) {
            Server.clients.removeByValue(ps);
            System.out.println(Server.clients.map.size());
            try {
                if (br != null) {
                    br.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private String getRelMsg(String line) {
        return line.substring(CrazyitProtocol.PROTOCOL_LENGTH, line.length() - CrazyitProtocol.PROTOCOL_LENGTH);
    }
}
