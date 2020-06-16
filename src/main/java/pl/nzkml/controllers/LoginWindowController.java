package pl.nzkml.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import pl.nzkml.SMSSceneManager;
import pl.nzkml.locales.Locales;

import java.io.IOException;
import java.util.Locale;

public class LoginWindowController {
    public Button loginButtonLoginWindow;
    public ComboBox languageComboBox;



    @FXML
    private void closeApplicationByExitButton(){
        Platform.exit();
    }
    @FXML
    private void loginUser() throws IOException {
        Platform.exit();
            }
    @FXML
    private void closeApplicationByMenuBar(){
        Platform.exit();
    }

    @FXML
    private void initialize(){

        languageComboBox.getSelectionModel().select(Locales.getInstance().getCurrentLanguage());
    }

    public void changeApplicationLanguage() {
        Locale locale = Locales.getInstance().getLocaleByLanguage((String)languageComboBox.getSelectionModel().selectedItemProperty().getValue());
        SMSSceneManager.getInstance().setLanguage(locale);
    }

    public void onShowing(Event event) {
        languageComboBox.getItems().clear();
        languageComboBox.getItems().addAll(Locales.getInstance().getLanguages());

    }
}
