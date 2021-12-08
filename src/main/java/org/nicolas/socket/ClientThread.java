package org.nicolas.socket;

import org.nicolas.controller.ChatController;
import org.nicolas.pojo.ChatHall;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author zorth
 */
public class ClientThread extends Thread {
    /**
     * 该客户端线程负责处理输入流
     */
    BufferedReader br = null;

    /**
     * 使用一个网络输入流来创建客户端线程
     */
    public ClientThread(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        try {
            String line = null;
            //从输入流读取数据
            while ((line = br.readLine()) != null) {

                synchronized(this){
                    ChatController.chatHall = ChatController.chatHall + line;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

