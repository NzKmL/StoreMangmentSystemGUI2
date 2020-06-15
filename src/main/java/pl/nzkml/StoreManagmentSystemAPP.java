package pl.nzkml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class StoreManagmentSystemAPP extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SMSSceneManager.getInstance().firstScene(stage);

    }

    public static void main(String[] args) {
        launch();
    }

}
