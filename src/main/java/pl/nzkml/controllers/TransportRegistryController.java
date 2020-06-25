package pl.nzkml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.SMSSceneManager;
import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.Category;
import pl.nzkml.datasource.model.RegistryElement;
import pl.nzkml.datasource.model.tableElement.TransportElement;
import pl.nzkml.datasource.model.tableElement.TransportRegistryTableElement;
import pl.nzkml.datasource.model.tableElement.TransportTableElement;
import pl.nzkml.datasource.model.Transport;
import pl.nzkml.datasource.repoException.RowNotFound;
import pl.nzkml.properties.ApplicationProperties;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransportRegistryController extends AbstractController {

    public Button acceptTransport;
    public Spinner categoryBoxQuantityField;
    public ComboBox categoryMetricField;
    public ComboBox categoryBoxSize;
    public TextField quantity;
    public TextField categoryName;
    public TextField transportID;
    public TableView<TransportRegistryTableElement> transportRegistryTable;
    public TableColumn transportTableIDCokumn;
    public TableColumn TransportTableCategoryNameColumn;
    public TableColumn transportTableQuantityColumn;
    public TableView<TransportTableElement> transportElementsTable;
    public Button removeTransport;
    public MenuItem closeMenuBarLoginWindow;

    public Button transportBackButton;
    public TableColumn<TransportRegistryTableElement, String> transportRegistryTableIDColumn;
    public TableColumn<TransportRegistryTableElement, String> transportRegistryTableDateColumn;
    public TableColumn<TransportRegistryTableElement, Integer> transportRegistryTableNumberOfCategoriesColumn;
    public TableColumn<TransportRegistryTableElement, String> transportRegistryTableIsAcceptedColumn;
    public Button addTransportButton;
    public Button transportRegistryRefreshButton;
    List<Transport> transportList;
    Map<String, Category> categoryMap;
    private static final Logger logger = LoggerFactory.getLogger(TransportRegistryController.class);

    @FXML
    private void initialize(){
        initializeCategoryMap();
        initializeTransportRegistryTableView();
    }

    private void initializeTransportRegistryTableView() {
        transportRegistryTable.getItems().clear();
        transportList =  RepositoryFactory.getInstance().createRepository(DataType.TRANSPORT).getAllElements();
        List<TransportRegistryTableElement> trte = new ArrayList<>();

        for(Transport transport : transportList){
           trte.add(
                    new TransportRegistryTableElement(
                            transport.getTransportID(),
                            new SimpleDateFormat("yyyy-mm-dd").format(transport.getDate()),
                            transport.getTransportElements().size(),
                            transport.getAccepted()? "+":"-"));
        }
        transportRegistryTableIDColumn.setCellValueFactory(new PropertyValueFactory<TransportRegistryTableElement, String>("transportID"));
        transportRegistryTableDateColumn.setCellValueFactory(new PropertyValueFactory<TransportRegistryTableElement, String>("date"));
        transportRegistryTableNumberOfCategoriesColumn.setCellValueFactory(new PropertyValueFactory<TransportRegistryTableElement, Integer>("numberOfCategories"));
        transportRegistryTableIsAcceptedColumn.setCellValueFactory(new PropertyValueFactory<TransportRegistryTableElement, String>("isAcceptred"));
        transportRegistryTable.getItems().addAll(trte);

    }

    private void initializeCategoryMap() {

        List<Category> categoryList = RepositoryFactory.getInstance().createRepository(DataType.CATEGORY).getAllElements();
        categoryMap = categoryList.stream().collect(
                Collectors.toMap(c->c.getName(), c->c));
    }

    public void acceptTransport(ActionEvent actionEvent) {
        TransportRegistryTableElement item =  transportRegistryTable.getSelectionModel().getSelectedItem();
        Transport transport = (Transport) RepositoryFactory.getInstance().createRepository(DataType.TRANSPORT).getByID(item.getTransportID());
        if(!transport.getAccepted().booleanValue()) {

            for (TransportElement transportElement : transport.getTransportElements()) {
                RegistryElement registryElement = new RegistryElement();
                registryElement.setCategoryID(transportElement.getCategoryID());
                registryElement.setNumberOfItems(transportElement.getQuantity());
                RepositoryFactory.getInstance().createRepository(DataType.MAIN_REGISTRY).add(registryElement);
            }
            transport.setAccepted(true);
            try {
                RepositoryFactory.getInstance().createRepository(DataType.TRANSPORT).update(transport);
            } catch (RowNotFound rowNotFound) {
                logger.error("Transport not found");
            }
        }

    }

    public void removeTransport(ActionEvent actionEvent) {
        TransportRegistryTableElement element = transportRegistryTable.getSelectionModel().getSelectedItem();
        if(!element.getIsAcceptred().equals("+")){
                try {
                    RepositoryFactory.getInstance().createRepository(DataType.TRANSPORT).removeByID(element.getTransportID());
                } catch (RowNotFound rowNotFound) {
                    logger.error("ROW NOT FOUND");
                }
                initializeTransportRegistryTableView();
        transportElementsTable.getItems().clear();
        }else{
            //TODO: pushup "nie wolno kasować przyjętych transportów
        }

    }

    public void selectTransportItem(MouseEvent mouseEvent) {
        TransportRegistryTableElement item =  transportRegistryTable.getSelectionModel().getSelectedItem();
        if(item==null){
            return;
        }
        Transport transport = (Transport) RepositoryFactory.getInstance().createRepository(DataType.TRANSPORT).getByID(item.getTransportID());

        if(transport!=null && transport.getTransportElements().size()>0) {
            List<TransportTableElement> list = TransportTableElement.convertToTableElementList(transport.getTransportElements());
            transportID.setText(item.getTransportID());
            transportElementsTable.getItems().clear();

            transportTableIDCokumn.setCellValueFactory(new PropertyValueFactory<TransportTableElement, Integer>("no"));
            TransportTableCategoryNameColumn.setCellValueFactory(new PropertyValueFactory<TransportTableElement, String>("categoryName"));
            transportTableQuantityColumn.setCellValueFactory(new PropertyValueFactory<TransportTableElement, String>("quantity"));
            transportElementsTable.getItems().addAll(list);
        }

    }

    public void selectCategoryItem(MouseEvent mouseEvent) {
        TransportTableElement item = transportElementsTable.getSelectionModel().getSelectedItem();
        if(item==null){
            return;
        }
        Category category =  categoryMap.get(item.getCategoryName());
        if(category!=null){
            categoryName.setText(category.getName());
            quantity.setText(item.getQuantity().toString());
            categoryMetricField.setValue(category.getMetric());
            categoryBoxQuantityField.getValueFactory().setValue(category.getBoxQuantity());
            categoryBoxSize.setValue(category.getBoxSize());

        }
    }

    public void addNewTransport(ActionEvent actionEvent) {
        SMSSceneManager.getInstance().openNewWindow(ApplicationProperties.ADD_TRANPOSRT_WINDOW);
    }

    public void refreshWidnow(ActionEvent actionEvent) {
        initialize();
    }
    public void backButtonAction(ActionEvent actionEvent) {
        Window window =   ((Node)(actionEvent.getSource())).getScene().getWindow();
        SMSSceneManager.getInstance().closeAdditionalWindow((Stage)window);
    }
}
