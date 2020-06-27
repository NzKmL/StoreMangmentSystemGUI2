package pl.nzkml.controllers;

import javafx.event.ActionEvent;
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
import pl.nzkml.datasource.model.Order;
import pl.nzkml.datasource.model.categoryListElement.OrderElement;
import pl.nzkml.datasource.model.tableElement.OrderTableElements;

import java.util.*;

public class AddOrderWindowController extends AbstractController{

    public TextField orderID;
    public Button saveTransportButton;
    public Button clearOrderTableButton;
    public TableColumn<OrderTableElements, String> orderTableQuantityColumn;
    public TableColumn<OrderTableElements, String> orderTableCategoryNameColumn;
    public TableColumn<OrderTableElements, Integer> orderTableIDColumn;
    public TableView <OrderTableElements> orderTable;
    public Button removeElementFromOrderTableButton;
    public Button addElementToOrted;
    public Spinner<Integer> numberOfElement;
    public ComboBox<String> categorySelect;
    public MenuItem closeMenuBarLoginWindow;
    public Button transportBuckButton;
    private Order order ;
    List<OrderTableElements> tableElements;
    int no=0;
    Map<String, Category> categoryMap;
    @FXML
    private void initialize()
    {   order = new Order();
        categoryMap=new HashMap<String, Category>();

        initCategorySelectField();
        tableElements=new ArrayList<>();
    }
    private void initCategorySelectField() {
        categorySelect.getItems().clear();
        List<Category> categoryList = RepositoryFactory.getInstance().createRepository(DataType.CATEGORY).getAllElements();
        categoryList.stream().forEach(a->categoryMap.put(a.getName(), a));
        categorySelect.getItems().addAll(categoryMap.keySet());
        System.out.println("Categoires reloaded");
    }

    public void addElementToTransportList(ActionEvent actionEvent) {
        OrderTableElements element = new OrderTableElements();
        element.setCategoryName(categorySelect.getSelectionModel().getSelectedItem());
        element.setNo(++no);
        element.setQuantity(numberOfElement.getValueFactory().getValue());
        tableElements.add(element);
        refresfOrderTable();
    }

    private void refresfOrderTable() {
        orderTable.getItems().clear();

        orderTableIDColumn.setCellValueFactory(new PropertyValueFactory<OrderTableElements, Integer>("no"));
        orderTableCategoryNameColumn.setCellValueFactory(new PropertyValueFactory<OrderTableElements, String>("categoryName"));
        orderTableQuantityColumn.setCellValueFactory(new PropertyValueFactory<OrderTableElements, String>("quantity"));
        orderTable.getItems().addAll(tableElements);
    }

    public void removetTableElement(ActionEvent actionEvent) {
        tableElements.remove(orderTable.getSelectionModel().getSelectedItem());
        refresfOrderTable();
    }

    public void clearTransportTable(ActionEvent actionEvent) {
        tableElements = new ArrayList<>();
        refresfOrderTable();
    }

    public void saveTransportButton(ActionEvent actionEvent) {
        order.setOrderID(orderID.getText());
        for (OrderTableElements element: tableElements) {
            order.getOrderElement().add(new OrderElement(element.getNo(), categoryMap.get(element.getCategoryName()).getId(), element.getQuantity()));
        }
        order.setOrderDate(new Date());
        order.setRealized(false);
        RepositoryFactory.getInstance().createRepository(DataType.ORDER).add(order);
        WMSSceneManager.getInstance().backToPreviosu();
    }
    public void backButtonAction(ActionEvent actionEvent) {
        Window window =   ((Node)(actionEvent.getSource())).getScene().getWindow();
        WMSSceneManager.getInstance().closeAdditionalWindow((Stage)window);
    }
}
