package pl.nzkml;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * JavaFX App
 */
public class WarehouseManagmentSystemAPP extends Application {

    private static final  Logger logger = LoggerFactory.getLogger(WarehouseManagmentSystemAPP.class);


    @Override
    public void start(Stage stage) throws IOException {
        logger.info("Application Start");
        stage.sizeToScene();
        WMSSceneManager.getInstance().firstScene(stage);

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
