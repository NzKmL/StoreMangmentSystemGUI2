package pl.nzkml.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import pl.nzkml.SMSSceneManager;

public abstract class AbstractController {
    public void closeApplicationByMenuBar(ActionEvent actionEvent) {
        Platform.exit();
    }
    public void backButtonAction(ActionEvent actionEvent) {
        SMSSceneManager.getInstance().backToPreviosu();
    }
}
