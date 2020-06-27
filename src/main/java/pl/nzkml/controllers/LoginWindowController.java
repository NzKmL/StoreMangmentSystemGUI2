package pl.nzkml.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.WMSSceneManager;
import pl.nzkml.authentication.AuthenticationService;
import pl.nzkml.locales.Locales;

import java.io.IOException;
import java.util.Locale;

public class LoginWindowController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(LoginWindowController.class);

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
            WMSSceneManager.getInstance().setScene("mainMenuDemo");
         logger.info("user login="+login+" został zalogowany");
        }
        else {
            loginWindowPasswordTextField.setText("");
            loginWindowsloginTextField.setText("");
            wrongLogError.setVisible(true);
            logger.info("user login="+login+" nie został zalogowany");
        }

    }
    public void backButtonAction(ActionEvent actionEvent) {
        Window window =   ((Node)(actionEvent.getSource())).getScene().getWindow();
        WMSSceneManager.getInstance().closeAdditionalWindow((Stage)window);
    }

    @FXML
    private void initialize(){
        languageComboBox.getSelectionModel().select(Locales.getInstance().getCurrentLanguage());
    }

    public void changeApplicationLanguage() {
        Locale locale = Locales.getInstance().getLocaleByLanguage((String)languageComboBox.getSelectionModel().selectedItemProperty().getValue());
        WMSSceneManager.getInstance().setLanguage(locale);
    }

    public void onShowing(Event event) {
        languageComboBox.getItems().clear();
        languageComboBox.getItems().addAll(Locales.getInstance().getLanguages());

    }
}
