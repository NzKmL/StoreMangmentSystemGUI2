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

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginWindow"));
        stage.setScene(scene);
        stage.show();
    }

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
    public static void main(String[] args) {
        launch();
    }

}
