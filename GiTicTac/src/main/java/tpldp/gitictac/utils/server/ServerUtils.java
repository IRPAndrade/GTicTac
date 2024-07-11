package tpldp.gitictac.utils.server;

import tpldp.gitictac.server.ServerLobbyController;

/**
 * Classe de utilidade para a comunicação entre diferentes classes do projeto.
 */
public final class ServerUtils {
    public static ServerLobbyController controller;
    public static int boardSize;

    /**
     * Envia para o controlador as informações necessárias.
     * @param ip
     * @param port
     */
    public static void setInfo(String ip, int port){
        controller.setInfo(ip, port);
    }
}