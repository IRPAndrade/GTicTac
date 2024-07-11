package tpldp.gitictac.utils.client;

import tpldp.gitictac.game.GameController;
import tpldp.gitictac.game.Move;

import java.util.concurrent.TimeUnit;

/**
 * Classe de utilidade para a comunicação entre diferentes classes do projeto.
 */
public final class GameUtils {
    public static GameController controller;
    public static int boardSize;

    /**
     * Envia um movinento a ser executado para o controlador do jogo.
     * @param object
     */
    public static void executeMove(Object object){

        controller.executeMove((Move) object);
    }

    /**
     * Envia uma peça para o controlador para ser atribuida.
     * @param piece
     */
    public static void setPlayer(String piece){
        try{
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        controller.setPiece(piece);
    }

    /**
     * Envia a ordem de permissão para o controlador.
     * @param play
     */
    public static void setPlay(Boolean play){
        controller.setPlay(play);
    }
}