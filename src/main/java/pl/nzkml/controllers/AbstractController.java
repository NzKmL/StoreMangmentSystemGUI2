package pl.nzkml.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;

public abstract class AbstractController {
    public void closeApplicationByMenuBar(ActionEvent actionEvent) {
        Platform.exit();
    }
}
