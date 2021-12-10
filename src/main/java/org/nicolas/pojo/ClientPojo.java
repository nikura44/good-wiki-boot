package org.nicolas.pojo;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 存储新建Client后的一些对象信息
 * @author zorth
 */
public class ClientPojo {
    /**
     * 存储Client所属用户名
     */
    private String nickname;

    private Socket socket;

    private BufferedReader bufferedReader;

    private PrintStream printStream;

    public ClientPojo(String nickname, Socket socket, BufferedReader bufferedReader, PrintStream printStream) {
        this.nickname = nickname;
        this.socket = socket;
        this.bufferedReader = bufferedReader;
        this.printStream = printStream;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }
}
