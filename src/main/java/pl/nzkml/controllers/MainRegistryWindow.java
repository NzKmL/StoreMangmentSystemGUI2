package pl.nzkml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import pl.nzkml.SMSSceneManager;
import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.Repository;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.Category;
import pl.nzkml.datasource.model.RegistryElement;
import pl.nzkml.datasource.model.Transport;
import pl.nzkml.datasource.model.tableElement.MainRegistryTableElement;
import pl.nzkml.datasource.model.tableElement.TransportRegistryTableElement;
import pl.nzkml.datasource.model.tableElement.TransportTableElement;
import pl.nzkml.properties.ApplicationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainRegistryWindow extends AbstractController {
    public Button transportBackButton;
    public MenuItem closeMenuBarLoginWindow;
    public Button transportsManagement;
    public TableView<MainRegistryTableElement> storeRegistryTable;
    public TableColumn< MainRegistryTableElement,String> storeRegistryCategoryName;
    public TableColumn< MainRegistryTableElement,Integer> storeRegistryItemsNumber;
    public TextField categoryName;
    public TextField quantity;
    public ComboBox categoryBoxSize;
    public ComboBox categoryMetricField;
    public Spinner categoryBoxQuantityField;
    public Button orderManagement;
    Map<String, Category> categoryMap;
    List<MainRegistryTableElement> mainTableList;

    private void initializeCategoryMap() {

        List<Category> categoryList = RepositoryFactory.getInstance().createRepository(DataType.CATEGORY).getAllElements();
        categoryMap = categoryList.stream().collect(
                Collectors.toMap(c->c.getName(), c->c));
    }

    @FXML
    private void initialize(){
        initializeCategoryMap();
        mainTableList = new ArrayList<>();
    initializeRegistryTable();

    }

    private void initializeRegistryTable() {
        storeRegistryTable.getItems().clear();
        mainTableList.clear();
        List<RegistryElement> registryElementList =   RepositoryFactory.getInstance().createRepository(DataType.MAIN_REGISTRY).getAllElements();

        if(registryElementList!=null && registryElementList.size()>0) {
            Repository<Category> repo = RepositoryFactory.getInstance().createRepository(DataType.CATEGORY);
            for (RegistryElement temp : registryElementList) {
                Category category = repo.getByID(temp.getCategoryID().toString());
                if (category != null) {
                    mainTableList.add(new MainRegistryTableElement(category.getName(), temp.getNumberOfItems()));
                }
            }
            storeRegistryCategoryName.setCellValueFactory(new PropertyValueFactory<MainRegistryTableElement, String>("categoryName"));
            storeRegistryItemsNumber.setCellValueFactory(new PropertyValueFactory<MainRegistryTableElement, Integer>("numberOfItems"));
            storeRegistryTable.getItems().addAll(mainTableList);
        }
    }

    public void openTransportManagement(ActionEvent actionEvent) {
        SMSSceneManager.getInstance().openNewWindow(ApplicationProperties.TRANSPORT_REGISTRY_WIDNOW);
    }

    public void selectRegistryTableItem(MouseEvent mouseEvent) {
        MainRegistryTableElement item =  storeRegistryTable.getSelectionModel().getSelectedItem();
        if(item==null){
            return;
        }


        Category category = categoryMap.get(item.getCategoryName());
        if(category!=null){
            categoryName.setText(category.getName());
            quantity.setText(item.getNumberOfItems().toString());
            categoryMetricField.setValue(category.getMetric());
            categoryBoxQuantityField.getValueFactory().setValue(category.getBoxQuantity());
            categoryBoxSize.setValue(category.getBoxSize());

        }
    }


    public void openOrderManagementWindow(ActionEvent actionEvent) {
       //TODO ORDERS
        // SMSSceneManager.getInstance().openNewWindow(ApplicationProperties.Ord);
    }
    public void backButtonAction(ActionEvent actionEvent) {
        Window window =   ((Node)(actionEvent.getSource())).getScene().getWindow();
        SMSSceneManager.getInstance().closeAdditionalWindow((Stage)window);
    }

    public void refreshStoreRegisterTable(ActionEvent actionEvent) {
        initializeRegistryTable();
    }
}
