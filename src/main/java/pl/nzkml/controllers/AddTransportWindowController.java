package pl.nzkml.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import pl.nzkml.WMSSceneManager;
import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.Category;
import pl.nzkml.datasource.model.Transport;
import pl.nzkml.datasource.model.categoryListElement.TransportElement;
import pl.nzkml.datasource.model.tableElement.TransportTableElement;
import pl.nzkml.properties.ApplicationProperties;

import java.util.*;

public class AddTransportWindowController extends AbstractController{
    public Button transportBuckButton;
    public MenuItem closeMenuBarLoginWindow;
    public Spinner<Integer> numberOfElement;
    public ComboBox<String> categorySelect;
    public Button addElementToTransportList;
    public Button removeElementFromTableButton;
    public TableView<TransportTableElement> transportTable;
    public TableColumn<TransportTableElement, Integer> transportTableIDColumn;
    public TableColumn<TransportTableElement, String> TransportTableCategoryNameColumn;
    public TableColumn<TransportTableElement, String> transportTableQuantityColumn;
    public Button clearTransportTableButton;
    public TextField transportID;
    public Button saveTransportButton;
    public Button openCategoryWindowButton;
    Transport transport;
    List<TransportTableElement> tableElements;
    int no=0;
    Map<String, Category> categoryMap=new HashMap<String, Category>();
    @FXML
    private void initialize()
    {   transport = new Transport();
       initCategorySelectField();
        tableElements=new ArrayList<>();
    }



    public void addElementToTransportList(ActionEvent actionEvent) {
        TransportTableElement element = new TransportTableElement();
        element.setCategoryName(categorySelect.getSelectionModel().getSelectedItem());
        element.setNo(++no);
        element.setQuantity(numberOfElement.getValueFactory().getValue());
        tableElements.add(element);
        refresfTransportTable();
    }

    private void refresfTransportTable() {
        transportTable.getItems().clear();

        transportTableIDColumn.setCellValueFactory(new PropertyValueFactory<TransportTableElement, Integer>("no"));
        TransportTableCategoryNameColumn.setCellValueFactory(new PropertyValueFactory<TransportTableElement, String>("categoryName"));
        transportTableQuantityColumn.setCellValueFactory(new PropertyValueFactory<TransportTableElement, String>("quantity"));
        transportTable.getItems().addAll(tableElements);
    }

    public void removetTableElement(ActionEvent actionEvent) {
        tableElements.remove(transportTable.getSelectionModel().getSelectedItem());
        refresfTransportTable();
    }

    public void clearTransportTable(ActionEvent actionEvent) {
        tableElements = new ArrayList<>();
        refresfTransportTable();
    }

    public void saveTransportButton(ActionEvent actionEvent) {

        transport.setTransportID(transportID.getText());
        for (TransportTableElement element: tableElements) {
            transport.getTransportElements().add(new TransportElement(element.getNo(), categoryMap.get(element.getCategoryName()).getId(), element.getQuantity()));
        }
        transport.setDate(new Date());
        transport.setAccepted(false);
        RepositoryFactory.getInstance().createRepository(DataType.TRANSPORT).add(transport);

        transportTable.getItems().clear();
        transportID.setText("");
    }

    private void initCategorySelectField() {
        categorySelect.getItems().clear();
        List<Category> categoryList = RepositoryFactory.getInstance().createRepository(DataType.CATEGORY).getAllElements();
        categoryList.stream().forEach(a->categoryMap.put(a.getName(), a));
        categorySelect.getItems().addAll(categoryMap.keySet());
        System.out.println("Categoires reloaded");
    }

    public void reloadCategories(Event event) {
        initCategorySelectField();
    }

    public void openCategoryWindow(ActionEvent actionEvent) {
        WMSSceneManager.getInstance().openNewWindow(ApplicationProperties.NEW_CATEGORY_WINDOW);
    }
    public void backButtonAction(ActionEvent actionEvent) {
        Window window =   ((Node)(actionEvent.getSource())).getScene().getWindow();
        WMSSceneManager.getInstance().closeAdditionalWindow((Stage)window);
    }
}
