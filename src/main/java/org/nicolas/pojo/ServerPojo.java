package org.nicolas.pojo;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerPojo {

    private String nickname;

    private PrintStream printStream;

    private BufferedReader bufferedReader;

    private Socket socket;

    private Boolean status;

    public ServerPojo(String nickname, PrintStream printStream, BufferedReader bufferedReader, Socket socket, Boolean status) {
        this.nickname = nickname;
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.socket = socket;
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
