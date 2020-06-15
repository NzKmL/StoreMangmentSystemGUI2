package pl.nzkml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * JavaFX App
 */
public class StoreManagmentSystemAPP extends Application {

    private static final  Logger logger = LoggerFactory.getLogger(StoreManagmentSystemAPP.class);


    @Override
    public void start(Stage stage) throws IOException {
        logger.info("Application Start");
        SMSSceneManager.getInstance().firstScene(stage);

    }

    @Override
    public void stop() throws Exception {
        logger.info("Application stop");
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }

}
