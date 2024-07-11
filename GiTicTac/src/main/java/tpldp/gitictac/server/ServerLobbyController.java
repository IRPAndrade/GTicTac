package tpldp.gitictac.server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import tpldp.gitictac.utils.SceneController;
import tpldp.gitictac.utils.server.ServerUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controla a "Scene" da atribuição do tamanho do tabuleiro, IP e Port do servidor.
 */
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

    /**
     * Inicializa as opções da comboBox da atribuição do tamanho do tabuleiro.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options = FXCollections.observableArrayList("5x5", "10x10");
        sizeComboBox.setItems(options);
    }

    /**
     * Volta para a "Scene" do menu principal.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        SceneController.mainMenu();
    }

    /**
     * Desativa a comboBox e o butão da atribuição do tamanho do tabuleiro e inicia o servidor.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onOpenServer(ActionEvent actionEvent) throws IOException {
        sizeComboBox.setDisable(true);
        chooseBtn.setDisable(true);
        int boardSize = sizeComboBox.getValue().equals("5x5") ? 5 : 10;

        ServerUtils.boardSize = boardSize;
        this.server = new Server();
    }

    /**
     * Escreve nas labels necessárias o IP e a Port do servidor.
     * @param ip
     * @param port
     */
    public void setInfo(String ip, int port){
        this.labelIP.setText(ip);
        this.labelPort.setText(String.valueOf(port));
    }
}