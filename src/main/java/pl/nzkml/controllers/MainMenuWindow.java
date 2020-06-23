package pl.nzkml.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import pl.nzkml.SMSSceneManager;
import pl.nzkml.properties.ApplicationProperties;

public class MainMenuWindow extends AbstractController {

    public Button mainMenuCategoryButton;

    public void openCategoryWindow(ActionEvent actionEvent) {
        SMSSceneManager.getInstance().setScene(ApplicationProperties.CATEGORY_LIST_WINDOW);
    }
}
