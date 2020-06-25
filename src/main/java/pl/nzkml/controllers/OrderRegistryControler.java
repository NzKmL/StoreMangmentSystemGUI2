package pl.nzkml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.SMSSceneManager;
import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.Category;
import pl.nzkml.datasource.model.Order;
import pl.nzkml.datasource.model.tableElement.OrderRegistryTableElement;
import pl.nzkml.datasource.model.tableElement.TransportRegistryTableElement;
import pl.nzkml.properties.ApplicationProperties;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderRegistryControler extends AbstractController {
    public Button orderBackButton;
    public MenuItem closeMenuBarLoginWindow;
    public Button removeOrder;
    public TableView orderElementsTable;
    public TableColumn orderTableIDCokumn;
    public TableColumn orderTableCategoryNameColumn;
    public TableColumn orderTableQuantityColumn;
    public TableColumn orderrElementStoreQuantity;
    public TextField orderID;
    public TableView<OrderRegistryTableElement> orderRegistryTable;
    public TableColumn<OrderRegistryTableElement, String> orderRegistryTableIDColumn;
    public TableColumn<OrderRegistryTableElement, String> orderRegistryTableDateColumn;
    public TableColumn<OrderRegistryTableElement,Integer> orderRegistryTableNumberOfCategoriesColumn;
    public TableColumn<OrderRegistryTableElement,String> orderRegistryTableStatusColumn;
    public TextField categoryName;
    public TextField quantity;
    public ComboBox categoryBoxSize;
    public ComboBox categoryMetricField;
    public Spinner categoryBoxQuantityField;
    public Button CheckOutOrderButton;
    public Button addOrderButton;
    public Button orderRegistryRefreshButton;

    List<Order> orderList;
    Map<String, Category> categoryMap;
    private static final Logger logger = LoggerFactory.getLogger(TransportRegistryController.class);
    @FXML
    private void initialize(){
        initializeCategoryMap();
        initializeOrderRegistryTableView();
    }

    private void initializeOrderRegistryTableView() {
        orderRegistryTable.getItems().clear();
        orderList =  RepositoryFactory.getInstance().createRepository(DataType.ORDER).getAllElements();
        List<OrderRegistryTableElement> trte = new ArrayList<>();
/*
        List<RegistryElement> registerList = RepositoryFactory.getInstance().createRepository(DataType.MAIN_REGISTRY).getAllElements();
        Map<Integer , Integer> registryMap = new HashMap<>();

        for(RegistryElement element : registerList){
            registryMap.put(element.getCategoryID(), element.getNumberOfItems());
        }





            String status = "";

            if(order.getRealized()){
                status =
            }

            for(OrderElement orderElement : order.getOrderElement()){
                if(registryMap.get(orderElement.getCategoryID())==null){
                    status = OrderStatus.notRea
                }
            }

            */


        for(Order order : orderList){
            trte.add(
                    new OrderRegistryTableElement(
                            order.getOrderID(),
                            new SimpleDateFormat("yyyy-MM-dd").format(order.getOrderDate()),
                            order.getOrderElement().size(),
                            ""));
        }
        orderRegistryTableIDColumn.setCellValueFactory(new PropertyValueFactory<OrderRegistryTableElement, String>("orderID"));
        orderRegistryTableDateColumn.setCellValueFactory(new PropertyValueFactory<OrderRegistryTableElement, String>("date"));
        orderRegistryTableNumberOfCategoriesColumn.setCellValueFactory(new PropertyValueFactory<OrderRegistryTableElement, Integer>("numberOfCategories"));
        orderRegistryTableStatusColumn.setCellValueFactory(new PropertyValueFactory<OrderRegistryTableElement, String>("status"));
        orderRegistryTable.getItems().addAll(trte);

    }

    private void initializeCategoryMap() {
        List<Category> categoryList = RepositoryFactory.getInstance().createRepository(DataType.CATEGORY).getAllElements();
        categoryMap = categoryList.stream().collect(
                Collectors.toMap(c->c.getName(), c->c));
    }


    public void removeOrder(ActionEvent actionEvent) {
    }

    public void selectCategoryItem(MouseEvent mouseEvent) {
    }

    public void selectOrderItem(MouseEvent mouseEvent) {
    }

    public void checkOutOrder(ActionEvent actionEvent) {
    }

    public void addNewOrder(ActionEvent actionEvent) {
        SMSSceneManager.getInstance().openNewWindow(ApplicationProperties.ADD_ORDER_WINDOW
        );
    }

    public void refreshWidnow(ActionEvent actionEvent) {
    }
}
