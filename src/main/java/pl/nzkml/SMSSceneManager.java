package pl.nzkml;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.locales.Locales;
import pl.nzkml.properties.ApplicationProperties;

import java.io.IOException;
import java.util.*;

public class SMSSceneManager {

    private static SMSSceneManager instance = null;
    private static final Logger logger = LoggerFactory.getLogger(StoreManagmentSystemAPP.class);
    private static Scene scene;
    private String fxml;
    private String fxmlBack;
    private Map<String, Stage> stagemap = new HashMap<>();
    private SMSSceneManager(){
    fxml = ApplicationProperties.START_FXML_FILE;
    }
    private Stage stage;

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
           logger.error(e.getMessage() + " " + e.getCause());
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
        this.stage = stage;
        stage.setScene(scene);
        stage.show();
    }

    public void openNewWindow(String fxml){
        logger.debug("New Window "+fxml);
        Scene scene = new Scene(loadFXML(fxml, Locales.getInstance().getCurrentLocate()));
        Stage stage = new Stage();
        stage.setTitle("Create new category");
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    public void setScene(String fxml){
        logger.debug("Change root to "+fxml);
        if(!fxml.equals(fxmlBack)){
        this.fxmlBack = this.fxml;}
        this.fxml = fxml;
        stage.setScene(new Scene(loadFXML(fxml, Locales.getInstance().getCurrentLocate())));
    }


    public void closeApplication() {
        Platform.exit();
    }

    public void backToPreviosu() {
        if(fxmlBack!=null && !fxmlBack.isEmpty()){
        setScene(fxmlBack);}
    }

    public void closeAdditionalWindow(Stage window){
        window.close();

    }
    public void refreshStage(){
        stage.close();
        stage.setScene(new Scene(loadFXML(fxml, Locales.getInstance().getCurrentLocate())));
        stage.show();
    }
}
