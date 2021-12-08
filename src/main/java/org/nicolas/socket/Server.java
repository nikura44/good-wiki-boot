package org.nicolas.socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zorth
 */
public class Server {
    private ThreadPoolExecutor executor;

    public Server(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    private static final int SERVER_PORT = 30000;
    /**
     * Use a Map object to hold the relationship between each customer name and the corresponding output stream
     */
    public static CrazyitMap<String, PrintStream> clients = new CrazyitMap<>();

    /**
     * initialize of Server
     */
    public void init() {
        try (
                // Establish listening ServerSocket
                ServerSocket ss = new ServerSocket(SERVER_PORT);
        ) {
            // An infinite loop is used to continuously receive requests from clients
            while (true) {
                Socket socket = ss.accept();

                executor.execute(new ServerThread(socket));
                System.out.println(executor.getActiveCount());
            }
        } catch (IOException exception) {
            System.out.println("PORT is used");
        }
    }

}
