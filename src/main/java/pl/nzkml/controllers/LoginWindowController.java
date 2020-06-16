package pl.nzkml.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.nzkml.SMSSceneManager;
import pl.nzkml.authentication.AuthenticationService;
import pl.nzkml.locales.Locales;

import java.io.IOException;
import java.util.Locale;

public class LoginWindowController {
    public Button loginButtonLoginWindow;
    public ComboBox languageComboBox;
    public TextField loginWindowsloginTextField;
    public PasswordField loginWindowPasswordTextField;
    public Text wrongLogError;


    @FXML
    private void closeApplicationByExitButton(){
        Platform.exit();
    }

    @FXML
    private void loginUser() throws IOException {

        String login =  loginWindowsloginTextField.getText();
        String password = loginWindowPasswordTextField.getText();

        AuthenticationService authenticationService = new AuthenticationService();

        if( authenticationService.isPasswordCorrect(login, password)){
           System.out.println("jest ok");
        }
        else {
            loginWindowPasswordTextField.setText("");
            loginWindowsloginTextField.setText("");
            wrongLogError.setVisible(true);
        }
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
