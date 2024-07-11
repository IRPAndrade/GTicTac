package tpldp.gitictac.utils.server;

import tpldp.gitictac.server.ServerLobbyController;

public final class ServerUtils {
    public static ServerLobbyController controller;
    public static int boardSize;

    public static void setInfo(String ip, int port){
        controller.setInfo(ip, port);
    }
}