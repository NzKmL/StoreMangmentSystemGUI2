package pl.nzkml;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SMSSceneManager {

    private SMSSceneManager(){

    }
    private static SMSSceneManager instance = null;
    private static final Logger logger = LoggerFactory.getLogger(StoreManagmentSystemAPP.class);

    public static SMSSceneManager getInstance() {
        logger.debug("create instance");
        if (instance == null) {
            instance = new SMSSceneManager();
        }
        return instance;
    }

    public void firstScene(Stage stage) throws IOException {
        logger.debug("First Scene start");
        scene = new Scene(loadFXML("loginWindow"));
        stage.setScene(scene);
        stage.show();
    }

    private static Scene scene;

    public static void setRoot(String fxml) throws IOException {
        logger.debug("Change root to "+fxml);
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        logger.debug("loadFXML method for "+fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(StoreManagmentSystemAPP.class.getResource("views/"+fxml + ".fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("labels", new Locale("pl")));
        return fxmlLoader.load();
    }

    public static void closeApplication()
    {

    }
}
