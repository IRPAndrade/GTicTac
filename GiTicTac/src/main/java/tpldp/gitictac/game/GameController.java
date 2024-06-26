package tpldp.gitictac.game;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import tpldp.gitictac.client.Client;
import tpldp.gitictac.utils.SceneController;
import tpldp.gitictac.utils.client.ClientUtils;
import tpldp.gitictac.utils.client.GameUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    //Tabuleiro
    @FXML private GridPane gridPane;
    private Button[][] buttons = new Button[GameUtils.boardSize][GameUtils.boardSize];

    //Jogador
    private String piece;
    private Boolean play = false;
    @FXML
    private Button btn00;

    public GameController(){
        GameUtils.controller = this;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Inicializa o array de botões
        for (int i = 0; i < GameUtils.boardSize; i++) {
            for (int j = 0; j < GameUtils.boardSize; j++) {
                String buttonId = "#btn" + i + j;
                buttons[i][j] = (Button) gridPane.lookup(buttonId);
            }
        }
    }

    @FXML
    private void handleButtonClick(javafx.event.ActionEvent event) {
        if(!play) return;
        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getText().isEmpty()) {
            //clickedButton.setText(piece);
            int row = GridPane.getRowIndex(clickedButton);
            int col = GridPane.getColumnIndex(clickedButton);
            ClientUtils.sendMove(new Move(row, col, piece));
        }
    }

    public void executeMove (Move move){
        Button btn = buttons[move.getRow()][move.getCol()];

        if (btn.getText().isEmpty()){
            btn.setText(move.getPlayer());
        }
    }

    private boolean checkWin(int row, int col) {
        String currentPlayerSymbol = piece;

        //Verificação na horizontal (esquerda e direita)
        if (countConsecutive(row, col, 0, 1) + countConsecutive(row, col, 0, -1) >= 4) {
            return true;
        }

        //Verificação na vertical (cima e baixo)
        if (countConsecutive(row, col, 1, 0) + countConsecutive(row, col, -1, 0) >= 4) {
            return true;
        }

        //Verificação na diagonal (superior esquerdo para inferior direito)
        if (countConsecutive(row, col, 1, 1) + countConsecutive(row, col, -1, -1) >= 4) {
            return true;
        }

        //Verificação na diagonal (superior direito para inferior esquerdo)
        if (countConsecutive(row, col, 1, -1) + countConsecutive(row, col, -1, 1) >= 4) {
            return true;
        }

        return false;
    }

    private int countConsecutive(int row, int col, int rowIncrement, int colIncrement) {
        int count = 0;
        String symbol = piece;
        int newRow = row + rowIncrement;
        int newCol = col + colIncrement;

        while (isValidPosition(newRow, newCol) && buttons[newRow][newCol].getText().equals(symbol)) {
            count++;
            newRow += rowIncrement;
            newCol += colIncrement;
        }

        return count;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 5 && col >= 0 && col < 5;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public void setPlay(Boolean play) {
        this.play = play;
    }
}
