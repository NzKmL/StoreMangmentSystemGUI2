package pl.nzkml;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SMSSceneManager {

    private SMSSceneManager(){

    }
    private static SMSSceneManager instance = null;

    public static SMSSceneManager getInstance() {
        if (instance == null) {
            instance = new SMSSceneManager();
        }
        return instance;
    }

    public void firstScene(Stage stage){
        try {
            scene = new Scene(loadFXML("loginWindow"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();
    }

    private static Scene scene;

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StoreManagmentSystemAPP.class.getResource("views/"+fxml + ".fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("labels", new Locale("pl")));
        return fxmlLoader.load();
    }

    public static void closeApplication()
    {

    }
}
