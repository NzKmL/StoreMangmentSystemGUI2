package pl.nzkml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pl.nzkml.SMSSceneManager;

public class MainMenuWindow extends AbstractController {

    public Button mainMenuCategoryButton;

    public void openCategoryWindow(ActionEvent actionEvent) {
        SMSSceneManager.getInstance().setRoot("CategoryWindow");
    }
}
