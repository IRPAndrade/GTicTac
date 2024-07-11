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

/**
 * Inicia o servidor
 */
public class Server {
    private int port;
    private String ip;
    private int boardSize;
    Game game;

    private ServerSocket serverSocket;

    Executor executor = Executors.newSingleThreadExecutor();

    public Server() {
        this.port = 1234;
        this.ip = getIp();
        this.boardSize = ServerUtils.boardSize;

        ServerUtils.setInfo(this.ip, this.port);

        executor.execute(this::startServer);
    }

    /**
     * Inicia o servidor, cria "Threads" para os jogadores e controla o número de jogadores conectados.
     */
    private void startServer() {
        game = new Game();
        int totalPlayers = 0;
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Server Accept Connections");

            while(totalPlayers < 2){
                Socket socket = serverSocket.accept();
                Player player = new Player(socket, game);
                game.addPlayer(player);
                player.sendMessage(ServerUtils.boardSize);
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

    /**
     * Envia a permissão para o inicio do jogo para os jogadores.
     */
    private void startGame() {
        for(Player player : game.getPlayers()){
            player.sendMessage(true);
        }
        System.out.println("Game started");
    }

    /**
     * Atribui uma peça a cada jogador de maneira aleatória.
     */
    private void randomOrder() {
        Random random = new Random();
        int result = random.nextInt(2)+1;
        for(int i = 0; i < 2; i++){
            if((result - 1) == i){
                game.getPlayers()[i].sendMessage("X");
                game.setCurrentPlayerIndex(i);
            } else{
                game.getPlayers()[i].sendMessage("O");
            }
        }
        System.out.println("Order setted");
    }

    /**
     * Retorna o endereço IP do servidor.
     * @return
     */
    private String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

}