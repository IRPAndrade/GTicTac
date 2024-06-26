package tpldp.gitictac.server;

import tpldp.gitictac.utils.server.ServerUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private String ip;
    private int boardSize;
    Game game;

    private ServerSocket serverSocket;

    Executor executor = Executors.newSingleThreadExecutor();

    public Server(int boardSize) {
        this.port = 1234;
        this.ip = getIp();
        this.boardSize = boardSize;

        ServerUtils.setInfo(this.ip, this.port);

        executor.execute(this::startServer);
    }

    private void startServer() {
        Game game = new Game();
        int totalPlayers = 0;
        try {
            serverSocket = new ServerSocket(this.port);
            while(totalPlayers < 2){
                System.out.println("Server Accept Connections");
                Socket socket = serverSocket.accept();
                Player player = new Player(socket, game);
                game.addPlayer(player);
                System.out.println("new player");

                Thread thread = new Thread(player);
                thread.start();
                totalPlayers++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        randomOrder();
        startGame();
    }

    private void startGame() {
        for(Player player : game.getPlayers()){
            player.sendMessage(true);
        }
    }

    private void randomOrder() {
        Random random = new Random();
        int result = random.nextInt(2)+1;
        for(int i = 0; i < 2; i++){
            if((result - 1) == i){
                game.getPlayers()[i].sendMessage("X");
            } else{
                game.getPlayers()[i].sendMessage("O");
            }
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