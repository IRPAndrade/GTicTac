package tpldp.gitictac.game;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import tpldp.gitictac.utils.client.ClientUtils;
import tpldp.gitictac.utils.client.GameUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    /*private Button[][] buttons = new Button[GameUtils.boardSize][GameUtils.boardSize];*/

    //Jogador
    private String piece;
    private Boolean play = false;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label currentPlayer;
    @FXML
    private Label myPiece;
    @FXML
    private Label victoryLabel;

    public GameController(){
        GameUtils.controller = this;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentPlayer.setText("X");
        for (var node : gridPane.getChildren()) {
            node.setOnMouseClicked(this::handleMouseClick);
        }
    }

    private void handleMouseClick(MouseEvent event) {
        var node = (Button) event.getSource();
        Integer row = GridPane.getRowIndex(node);
        Integer col = GridPane.getColumnIndex(node);
        System.out.println("Clicked cell at: Row " + row + ", Column " + col);
        ClientUtils.sendMove(new Move(row, col, piece));
        System.out.println("Send Move");
    }

    public void executeMove (Move move){
        Platform.runLater(()->{

            if(move.getCol() < 0 && move.getRow() < 0){
                for(var node : gridPane.getChildren()){
                    Button btn = (Button) node;
                    btn.setText("");
                    victoryLabel.setText(move.getMessage());
                }
                return;
            }

            victoryLabel.setText("");

            for (var node : gridPane.getChildren()){
                if(GridPane.getRowIndex(node) == move.getRow() && GridPane.getColumnIndex(node) == move.getCol()){
                    Button btn = (Button) node;
                    btn.setText(move.getMessage());
                    if(move.getMessage().equals("X")){
                        currentPlayer.setText("O");
                    } else{
                        currentPlayer.setText("X");
                    }
                }
            }
            System.out.println("move executed");
        });
    }

    public void setPiece(String piece) {

        Platform.runLater(()->{
            myPiece.setText(piece);
        });

        this.piece = piece;
        System.out.println(piece);
    }

    public void setPlay(Boolean play) {
        this.play = play;
    }

}
