package pl.nzkml.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import pl.nzkml.WMSSceneManager;
import pl.nzkml.properties.ApplicationProperties;

public class MainMenuWindow extends AbstractController {

    public Button mainMenuCategoryButton;

    public void openCategoryWindow(ActionEvent actionEvent) {
        WMSSceneManager.getInstance().setScene(ApplicationProperties.CATEGORY_LIST_WINDOW);
    }

    public void openOrdersWindow(ActionEvent actionEvent) {
        WMSSceneManager.getInstance().setScene(ApplicationProperties.ORDER_REGISTRY_WINDOW);
    }

    public void openTransportWindow(ActionEvent actionEvent) {
        WMSSceneManager.getInstance().setScene(ApplicationProperties.TRANSPORT_REGISTRY_WINDOW);
    }

    public void openRegisterWindow(ActionEvent actionEvent) {
        WMSSceneManager.getInstance().setScene(ApplicationProperties.MAIN_REGISTRY_WINDOW);
    }


}
