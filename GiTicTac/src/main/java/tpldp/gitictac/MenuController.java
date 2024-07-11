package tpldp.gitictac;

import javafx.application.Platform;
import tpldp.gitictac.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Classe responsável pelo controlo do menu principal.
 */
public class MenuController {

    /**
     * Navega da "Scene" do menu principal para a "Scene" do servidor.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onCreateServer(ActionEvent actionEvent) throws IOException {
        SceneController.showLobby();
    }

    /**
     * Navega da "Scene" do menu principal para a "Scene" de conexão ao servidor.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onConnectServer(ActionEvent actionEvent) throws IOException {
        SceneController.showConnectLobby();
    }

    /**
     * Fecha a aplicação.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onExit(ActionEvent actionEvent) throws IOException {
        Platform.exit();
    }
}