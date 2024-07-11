package tpldp.gitictac.utils.client;

import tpldp.gitictac.client.Client;
import tpldp.gitictac.game.Move;

import java.io.IOException;

/**
 * Classe de utilidade para a comunicação entre diferentes classes do projeto.
 */
public final class ClientUtils {
    public static Client client;

    /**
     * Inicia um "Listener" que fica á espera de mensagens e dependendo to tipo da mensagem dá a tratamento necessário.
     */
    public static void startClientListener(){
        new Thread(() -> {
            try {
                while(true){
                    Object object = ClientUtils.client.receiveMove();
                    if(object instanceof Integer){
                        GameUtils.boardSize = (Integer) object;
                    }else if(object instanceof String){
                        GameUtils.setPlayer((String) object);
                    }else if(object instanceof Boolean){
                        GameUtils.setPlay((Boolean) object);
                    }else if(object instanceof Move){
                        GameUtils.executeMove(object);
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Envia um movimento para o cliente.
     * @param move
     */
    public static void sendMove(Move move){
        try{
            client.sendMove(move);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}