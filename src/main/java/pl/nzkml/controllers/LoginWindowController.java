package pl.nzkml.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginWindowController {
    @FXML
    private void closeApplicationByExitButton() throws IOException {
        Platform.exit();
    }
    @FXML
    private void loginUser() throws IOException {
        Platform.exit();
            }
    @FXML
    private void closeApplicationByMenuBar() throws IOException {
        Platform.exit();
    }

}
