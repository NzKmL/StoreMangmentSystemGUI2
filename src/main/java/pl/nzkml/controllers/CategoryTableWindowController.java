package pl.nzkml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.SMSSceneManager;
import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.Category;
import pl.nzkml.datasource.repoException.RowNotFound;
import pl.nzkml.properties.ApplicationProperties;

import java.util.List;

public class CategoryTableWindowController extends AbstractController {
    public Button refreshCategoryTableButton;
    Logger logger = LoggerFactory.getLogger(getClass().getName());
    public TableView<Category> categoryTable;
    public Button categoryBackButton;
    public Button addNewCategoryButton;
    public Button deleteCategoryButton;
    public TableColumn<Category,String> tableCategoryID;
    public TableColumn<Category,String> tableCategoryName;
    public TableColumn<Category,String> tableCategoryMetric;
    public TableColumn<Category,Integer> tableCategoryQuantity;
    public TableColumn<Category,String> tableCategoryBoxSize;

    @FXML
    private void initialize(){
        initCategoryTable();
    }

    private void initCategoryTable() {
        categoryTable.getItems().clear();

        tableCategoryID.setCellValueFactory(new PropertyValueFactory<Category, String>("id"));
        tableCategoryName.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        tableCategoryMetric.setCellValueFactory(new PropertyValueFactory<Category, String>("metric"));
        tableCategoryQuantity.setCellValueFactory(new PropertyValueFactory<Category, Integer>("boxQuantity"));
        tableCategoryBoxSize.setCellValueFactory(new PropertyValueFactory<Category, String>("boxSize"));

        List<Category> categoryList =  RepositoryFactory.getInstance().createRepository(DataType.CATEGORY).getAllElements();
        categoryTable.getItems().addAll(categoryList);

    }

    public void addNewCategory(ActionEvent actionEvent) {
        SMSSceneManager.getInstance().openNewWindow(ApplicationProperties.NEW_CATEGORY_WINDOW);
    }
    public void deleteCategory(ActionEvent actionEvent) {
        try {
            RepositoryFactory.getInstance().createRepository(DataType.CATEGORY).removeByID(categoryTable.getSelectionModel().getSelectedItem().getId().toString());
        } catch (RowNotFound rowNotFound) {
            logger.error("Row not found");
            rowNotFound.printStackTrace();
        }
        initCategoryTable();
    }

    public void refreshCategoryTable(ActionEvent actionEvent) {
        initCategoryTable();
    }
}
