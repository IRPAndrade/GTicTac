package tpldp.gitictac.server;

import tpldp.gitictac.utils.server.ServerUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;

public class Server {
    private int port;
    private String ip;

    private ServerSocket serverSocket;
    private List<Player> players;

    Executor executor = Executors.newSingleThreadExecutor();

    public Server() {
        this.port = 1234;
        this.ip = getIp();
        this.players = new ArrayList<>();

        ServerUtils.setInfo(this.ip, this.port);

        executor.execute(this::startServer);
    }

    private void startServer() {
        try {
            serverSocket = new ServerSocket(this.port);
            while(true){
                System.out.println("Server Accept Connections");
                Socket socket = serverSocket.accept();
                Player player = new Player(socket);
                this.players.add(player);
                System.out.println("new player");

                Thread thread = new Thread(player);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

}