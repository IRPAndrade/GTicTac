package tpldp.gitictac;

import javafx.application.Platform;
import tpldp.gitictac.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MenuController {

    @FXML
    public void onCreateServer(ActionEvent actionEvent) throws IOException {
        SceneController.showLobby();
    }

    @FXML
    public void onConnectServer(ActionEvent actionEvent) throws IOException {
        SceneController.showConnectLobby();
    }

    @FXML
    public void onExit(ActionEvent actionEvent) throws IOException {
        Platform.exit();
    }
}