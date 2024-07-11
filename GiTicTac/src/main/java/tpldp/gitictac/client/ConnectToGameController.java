package tpldp.gitictac.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tpldp.gitictac.utils.SceneController;
import tpldp.gitictac.utils.client.ClientUtils;
import tpldp.gitictac.utils.client.GameUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ConnectToGameController
{
    @FXML
    private TextField portText;
    @FXML
    private TextField ipText;

    @FXML
    public void connect(ActionEvent actionEvent) throws IOException {
        ClientUtils.client = new Client(ipText.getText(), Integer.parseInt(portText.getText()));
        ClientUtils.startClientListener();
        try{
            TimeUnit.MILLISECONDS.sleep(30);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        if(GameUtils.boardSize == 5){
            SceneController.showGame5();
        }else{
            SceneController.showGame10();
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneController.mainMenu();
    }
}