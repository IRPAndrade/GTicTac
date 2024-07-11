package tpldp.gitictac;

import tpldp.gitictac.utils.SceneController;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe main.
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneController.stage = stage;
        SceneController.mainMenu();
    }

    public static void main(String[] args) {
        launch();
    }
}