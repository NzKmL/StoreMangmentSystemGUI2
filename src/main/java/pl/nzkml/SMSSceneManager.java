package pl.nzkml;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.locales.Locales;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class SMSSceneManager {

    private static SMSSceneManager instance = null;
    private static final Logger logger = LoggerFactory.getLogger(StoreManagmentSystemAPP.class);
    private static Scene scene;
    private String fxml;

    private SMSSceneManager(){
    fxml = "loginWindow";
    }

    public static SMSSceneManager getInstance() {
        logger.debug("create instance");
        if (instance == null) {
            instance = new SMSSceneManager();
        }
        return instance;
    }

    private Parent loadFXML(String fxml, Locale locale)  {
        logger.debug("loadFXML method for "+fxml);
        Parent parent=null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/"+fxml + ".fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("labels", locale));
        try {
            parent =  fxmlLoader.load();
        } catch (IOException e) {
           logger.error("fxml file load error");
           logger.error(e.getMessage());
           Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
           closeApplication();
        }
        return parent;
    }


    public void setLanguage(Locale locale){
        Locales.getInstance().setCurrentLocate(locale);
        scene.setRoot(loadFXML(fxml, locale));
    }

    public void firstScene(Stage stage){
        logger.debug("First Scene start");
        scene = new Scene(loadFXML(fxml, Locales.getInstance().getCurrentLocate()));
        stage.setScene(scene);
        stage.show();
    }

    public void setRoot(String fxml){
        logger.debug("Change root to "+fxml);
        this.fxml = fxml;
        scene.setRoot(loadFXML(fxml, Locales.getInstance().getCurrentLocate()));
    }

    public  void closeApplication() {
        Platform.exit();
    }
}
