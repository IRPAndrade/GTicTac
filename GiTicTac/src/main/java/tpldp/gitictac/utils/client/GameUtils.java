package tpldp.gitictac.utils.client;

import tpldp.gitictac.game.GameController;
import tpldp.gitictac.game.Move;

import java.util.concurrent.TimeUnit;

public final class GameUtils {
    public static GameController controller;
    public static int boardSize;

    public static void executeMove(Object object){
        controller.executeMove((Move) object);
    }

    public static void setPlayer(String piece){
        try{
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        controller.setPiece(piece);
    }

    public static void setPlay(Boolean play){
        controller.setPlay(play);
    }
}