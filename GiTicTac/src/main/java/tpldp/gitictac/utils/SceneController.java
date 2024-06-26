package tpldp.gitictac.utils;

import tpldp.gitictac.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class SceneController {
    public static Stage stage;

    public static void mainMenu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 594, 418);
        stage.setTitle("Menu Principal");
        stage.setScene(scene);
        stage.show();
    }

    public static void showLobby() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("server/serverLobby.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 594, 418);
        stage.setTitle("Informações do Servidor");
        stage.setScene(scene);
        stage.show();
    }

    public static void showConnectLobby() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("client/clientConnect.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 594, 418);
        stage.setTitle("Coneção ao Servidor");
        stage.setScene(scene);
        stage.show();
    }

    public static void showGame5() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("client/game5x5.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Giant Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void showGame10() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("client/game10x10.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Giant Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }
}