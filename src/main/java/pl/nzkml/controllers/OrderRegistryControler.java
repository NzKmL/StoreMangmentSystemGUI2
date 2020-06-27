package pl.nzkml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.WMSSceneManager;
import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.Category;
import pl.nzkml.datasource.model.Order;
import pl.nzkml.datasource.model.RegistryElement;
import pl.nzkml.datasource.model.categoryListElement.OrderElement;
import pl.nzkml.datasource.model.tableElement.OrderElementTableElement;
import pl.nzkml.datasource.model.tableElement.OrderRegistryTableElement;
import pl.nzkml.datasource.repoException.RowNotFound;
import pl.nzkml.datasource.OrderStatus;
import pl.nzkml.locales.Locales;
import pl.nzkml.properties.ApplicationProperties;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class OrderRegistryControler extends AbstractController {
    public Button orderBackButton;
    public MenuItem closeMenuBarLoginWindow;
    public Button removeOrderButton;
    public TableView<OrderElementTableElement> orderElementsTable;
    public TableColumn<OrderElementTableElement,Integer> orderTableIDCokumn;
    public TableColumn<OrderElementTableElement,String> orderTableCategoryNameColumn;
    public TableColumn<OrderElementTableElement,Integer> orderTableQuantityColumn;
    public TableColumn<OrderElementTableElement,Integer> orderrElementWarehouseQuantity;
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
    public Button checkOutOrderButton;
    public Button addOrderButton;
    public Button orderRegistryRefreshButton;


    List<Order> orderList;
    Map<String, Category> categoryMap;
    Map<Integer, Integer> currentWarehouseStateMap;
    private static final Logger logger = LoggerFactory.getLogger(TransportRegistryController.class);
    @FXML
    private void initialize(){
        checkOutOrderButton.setDisable(true);
        currentWarehouseStateMap = new HashMap<>();
        updateCurrentWarehouseStateMap();
        initializeCategoryMap();
        initializeOrderRegistryTableView();

    }

    private void initializeOrderRegistryTableView() {
        updateCurrentWarehouseStateMap();
        orderRegistryTable.getItems().clear();
        orderList =  RepositoryFactory.getInstance().createRepository(DataType.ORDER).getAllElements();
        List<OrderRegistryTableElement> trte = new ArrayList<>();

        String status = "";



        for(Order order : orderList){
            if(order.getRealized()){
                status = getStatusLabel(OrderStatus.REALIZED);
            }else {
                status = getStatusLabel(OrderStatus.NOT_REALIZED);
                for (OrderElement orderElement : order.getOrderElement()) {
                    if(currentWarehouseStateMap.get(orderElement.getCategoryID())==null
                            ||orderElement.getQuantity()> currentWarehouseStateMap.get(orderElement.getCategoryID())){
                        status=getStatusLabel(OrderStatus.NOT_ENAUGHT);
                    }

                }

            }

            trte.add(
                    new OrderRegistryTableElement(
                            order.getOrderID(),
                            new SimpleDateFormat("yyyy-MM-dd").format(order.getOrderDate()),
                            order.getOrderElement().size(),
                            status));
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


        OrderRegistryTableElement element = orderRegistryTable.getSelectionModel().getSelectedItem();
      /*  if(!element.get().equals("+")){
      */
        try {
            RepositoryFactory.getInstance().createRepository(DataType.ORDER).removeByID(element.getOrderID());
        } catch (RowNotFound rowNotFound) {
            logger.error("ROW NOT FOUND");
            rowNotFound.printStackTrace();
        }

        initializeOrderRegistryTableView();
        orderElementsTable.getItems().clear();

       /* else{
            //TODO: pushup "nie wolno kasować przyjętych orderów
        }*/


    }

    public void selectCategoryItem(MouseEvent mouseEvent) {
       OrderElementTableElement orderElementTableElement =  orderElementsTable.getSelectionModel().getSelectedItem();
       if(orderElementTableElement!=null) {
           categoryName.setText(orderElementTableElement.getCategoryName());
           quantity.setText(String.valueOf(orderElementTableElement.getQuantity()));
           categoryBoxSize.setValue(categoryMap.get(orderElementTableElement.getCategoryName()).getBoxSize());
           categoryMetricField.setValue(categoryMap.get(orderElementTableElement.getCategoryName()).getMetric());
       }
    }

    public void selectOrderItem(MouseEvent mouseEvent) {
        if(orderRegistryTable.getSelectionModel().getSelectedItem()!=null)
        {
            loadOrderElementTable();

            if(orderRegistryTable.getSelectionModel().getSelectedItem().getStatus().equals(getStatusLabel(OrderStatus.NOT_REALIZED))){
                checkOutOrderButton.setDisable(false);
            }else if(orderRegistryTable.getSelectionModel().getSelectedItem().getStatus().equals(getStatusLabel(OrderStatus.REALIZED))){
                checkOutOrderButton.setDisable(false);
                removeOrderButton.setDisable(false);
            }
            else{
                checkOutOrderButton.setDisable(true);
            }


        }
    }

    private void loadOrderElementTable() {
        updateCurrentWarehouseStateMap();
        orderElementsTable.getItems().clear();
        List<OrderElement> orderElements = ((Order)RepositoryFactory.getInstance().createRepository(DataType.ORDER).getByID(orderRegistryTable.getSelectionModel().getSelectedItem().getOrderID())).getOrderElement();
        List<OrderElementTableElement> orderElementTable = OrderElementTableElement.getOrderElementTableElement(orderElements, currentWarehouseStateMap);

        orderID.setText(orderRegistryTable.getSelectionModel().getSelectedItem().getOrderID());

        orderTableIDCokumn.setCellValueFactory(new PropertyValueFactory<>("lp"));
        orderTableCategoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        orderTableQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        orderrElementWarehouseQuantity.setCellValueFactory(new PropertyValueFactory<>("inventory"));

        orderElementsTable.getItems().addAll(orderElementTable);
    }

    private void updateCurrentWarehouseStateMap(){

        List<RegistryElement> list =  RepositoryFactory.getInstance().createRepository(DataType.MAIN_REGISTRY).getAllElements();
        currentWarehouseStateMap =  list.stream().collect(Collectors.toMap(a->a.getCategoryID(), a->a.getNumberOfItems()));

        if(currentWarehouseStateMap ==null){
            currentWarehouseStateMap = new HashMap<>();
        }
    }

    public void checkOutOrder(ActionEvent actionEvent) {
        Order order = (Order) RepositoryFactory.getInstance().createRepository(DataType.ORDER).getByID(orderID.getText());
         for(OrderElement element : order.getOrderElement()){
            RegistryElement registryElement = (RegistryElement) RepositoryFactory.getInstance().createRepository(DataType.MAIN_REGISTRY).getByID(String.valueOf(element.getCategoryID()));

            Integer newState =  registryElement.getNumberOfItems()-element.getQuantity();
             registryElement.setNumberOfItems(newState);
             try {
                 RepositoryFactory.getInstance().createRepository(DataType.MAIN_REGISTRY).update(registryElement);
             } catch (RowNotFound rowNotFound) {
                 logger.error("ROW NOT FOUND");
                 rowNotFound.printStackTrace();
             }
         }
         order.setRealized(true);
        try {
            RepositoryFactory.getInstance().createRepository(DataType.ORDER).update(order);
        } catch (RowNotFound rowNotFound) {
            logger.error("ROW NOT FOUND");
            rowNotFound.printStackTrace();
        }
        initialize();
    }

    public void addNewOrder(ActionEvent actionEvent) {
        WMSSceneManager.getInstance().openNewWindow(ApplicationProperties.ADD_ORDER_WINDOW
        );
    }

    public void refreshWidnow(ActionEvent actionEvent) {
        orderElementsTable.getItems().clear();
        initialize();
    }
    private String getStatusLabel(OrderStatus orderStatus){
        return ResourceBundle.getBundle("labels", Locales.getInstance().getCurrentLocate()).getString(orderStatus.getLabelKey());
    }
}
