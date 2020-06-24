package pl.nzkml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import pl.nzkml.SMSSceneManager;
import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.Category;
import pl.nzkml.locales.Locales;

import java.util.ResourceBundle;

public class CategoryWindowController extends AbstractController {
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private ComboBox<String> categoryBoxSize;
    @FXML
    private ComboBox<String> categoryMetricField;
    @FXML
    private Spinner<Integer> categoryBoxQuantityField;

    @FXML
    private void initialize(){


        initCategoryMetricField();
        initCategoryBoxSize();


    }

    private void initCategoryBoxSize() {
        categoryBoxSize.getItems().clear();
        categoryBoxSize.getItems().addAll( ResourceBundle.getBundle("labels", Locales.getInstance().getCurrentLocate()).getString("categoryBoxSize").split(";"));
        categoryBoxSize.getSelectionModel().select(0);
    }


    private void initCategoryMetricField() {
        categoryMetricField.getItems().clear();
        categoryMetricField.getItems().addAll( ResourceBundle.getBundle("labels", Locales.getInstance().getCurrentLocate()).getString("categoryType").split(";"));
        categoryMetricField.getSelectionModel().select(0); ;
    }
    public void backButtonAction(ActionEvent actionEvent) {
        Window window =   ((Node)(actionEvent.getSource())).getScene().getWindow();
        SMSSceneManager.getInstance().closeAdditionalWindow((Stage)window);
    }

    public void cleanButtonAction(ActionEvent actionEvent) {
        categoryNameTextField.setText("") ;
        categoryBoxSize.getSelectionModel().select(0);
        categoryMetricField.getSelectionModel().select(0); ;
        categoryBoxQuantityField.getValueFactory().setValue(0);
    }

    public void saveButtonAction(ActionEvent actionEvent) {
        Category category = new Category();
        category.setBoxQuantity(categoryBoxQuantityField.getValue());
        category.setBoxSize(categoryBoxSize.getSelectionModel().getSelectedItem());
        category.setName(categoryNameTextField.getText());
        category.setMetric(categoryMetricField.getSelectionModel().getSelectedItem());
        RepositoryFactory.getInstance().createRepository(DataType.CATEGORY).add(category);

        Window window =   ((Node)(actionEvent.getSource())).getScene().getWindow();
        SMSSceneManager.getInstance().closeAdditionalWindow((Stage)window);
    }
}
