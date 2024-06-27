package tpldp.gitictac.server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import tpldp.gitictac.utils.SceneController;
import tpldp.gitictac.utils.server.ServerUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerLobbyController implements Initializable {
    private Server server;
    @FXML
    private Label labelIP;
    @FXML
    private Label labelPort;
    @FXML
    private ComboBox sizeComboBox;
    @FXML
    private Button chooseBtn;

    public ServerLobbyController(){
        ServerUtils.controller = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options = FXCollections.observableArrayList("5x5", "10x10");
        sizeComboBox.setItems(options);
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneController.mainMenu();
    }

    @FXML
    public void onOpenServer(ActionEvent actionEvent) throws IOException {
        sizeComboBox.setDisable(true);
        chooseBtn.setDisable(true);
        int boardSize = sizeComboBox.getValue().equals("5x5") ? 5 : 10;

        this.server = new Server(boardSize);
    }

    public void setInfo(String ip, int port){
        this.labelIP.setText(ip);
        this.labelPort.setText(String.valueOf(port));
    }
}