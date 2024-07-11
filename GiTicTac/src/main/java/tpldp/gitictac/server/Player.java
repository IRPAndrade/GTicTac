package tpldp.gitictac.server;

import tpldp.gitictac.game.Move;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * Responsável pela comunicação entre o servidor e o cliente.
 */
public class Player implements Runnable, Serializable {
    private Socket socket;
    private final transient ObjectInputStream input;
    private final transient ObjectOutputStream output;
    private Game game;


    public Player(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
        try {
            this.input = new ObjectInputStream(socket.getInputStream());
            this.output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recebe e processa o movimento do lado do servidor.
     */
    @Override
    public void run() {
        Object object;

        while(true){
            try{
                Move move = (Move) input.readObject();
                game.processMove(move, this);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * Evia uma mensagem ou movimento para o cliente.
     * @param move
     */
    public void sendMessage(Object move) {
        try {
            output.writeObject(move);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}