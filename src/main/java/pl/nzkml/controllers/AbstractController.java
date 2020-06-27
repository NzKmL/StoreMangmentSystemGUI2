package pl.nzkml.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import pl.nzkml.WMSSceneManager;

public abstract class AbstractController {
    public void closeApplicationByMenuBar(ActionEvent actionEvent) {
        Platform.exit();
    }
    public void backButtonAction(ActionEvent actionEvent) {
        WMSSceneManager.getInstance().backToPreviosu();
    }
}
