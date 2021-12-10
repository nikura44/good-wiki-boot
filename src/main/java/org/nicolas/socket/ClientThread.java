package org.nicolas.socket;

import org.nicolas.controller.ChatController;
import org.nicolas.pojo.ChatHall;
import org.nicolas.pojo.ClientPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author zorth
 */
public class ClientThread extends Thread {
    private final Logger logger = LoggerFactory.getLogger(ClientThread.class);
    /**
     * 该客户端线程负责处理输入流
     */
    BufferedReader br = null;



    /**
     * 标记线程状态｜状态变化则要关闭线程
     */
    Boolean runFlag = true;
    String nickname;

    /**
     * 使用一个网络输入流来创建客户端线程
     */
    public ClientThread(BufferedReader br,String nickname) {
        this.br = br;
        this.nickname = nickname;
    }

    @Override
    public void run() {
        try {
            String line = null;
            //从输入流读取数据
            while ((line = br.readLine()) != null && runFlag) {

                synchronized(this){

                    logger.info(line);
                    ChatController.chatHall.add(line);

                    for (ClientPojo pojo : ChatController.pojoList) {
                        if (pojo.getNickname().equals(nickname)) {
                            runFlag = pojo.isStatus();
                        }
                    }
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

