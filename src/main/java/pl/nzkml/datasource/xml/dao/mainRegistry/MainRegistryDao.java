package pl.nzkml.datasource.xml.dao.mainRegistry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.datasource.CrudDao;
import pl.nzkml.datasource.model.RegistryElement;
import pl.nzkml.datasource.repoException.RowNotFound;
import pl.nzkml.datasource.xml.file.FileProcessor;
import pl.nzkml.properties.ApplicationProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainRegistryDao implements CrudDao<RegistryElement> {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<RegistryElement> itemsList;
    @Override
    public void insert(RegistryElement registryElement) {
        nullOrEmptyOperation();



        RegistryElement registryElementFromFile = select(registryElement.getCategoryID());
        if(registryElementFromFile!=null){
            registryElementFromFile.setNumberOfItems(registryElementFromFile.getNumberOfItems() + registryElement.getNumberOfItems())  ;
            try {
                update(registryElementFromFile);
            } catch (RowNotFound rowNotFound) {
                logger.error("unknowne error with update registryElement");
            }
        }else {
            itemsList.add(registryElement);
            saveToFile();
        }
    }
    @Override
    public RegistryElement select(Object categoryID) {
        if (categoryID==null) return null;

        nullOrEmptyOperation();
        if (itemsList.isEmpty()) {
            return null;
        }else{
            return itemsList.stream().filter(temp -> categoryID.equals(String.valueOf(temp.getCategoryID()))).findAny().orElse(null);
        }
    }

    @Override
    public List<RegistryElement> selectALL() {
        XmlMapper mapper = new XmlMapper();

        FileProcessor fileProcessor = new FileProcessor();
        String xml = null;
        try {
            xml = fileProcessor.readFile(ApplicationProperties.MAIN_WAREHOUSE_REGISTRY_FILE_PATH);
           if(!xml.isEmpty()) {
               WarehouseRegistryListContainerToXml userListXmlContainer = new WarehouseRegistryListContainerToXml();
                userListXmlContainer = mapper.readValue(xml, userListXmlContainer.getClass());
               itemsList =userListXmlContainer.getDataList();
           } else {
               itemsList = new ArrayList<>();
           }
        }
        catch (FileNotFoundException e) {
            logger.error("FileNotFoundException at the UserDaoXML selectALL method " + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException at the UserDaoXML selectALL method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        }
        if(itemsList==null)itemsList=new ArrayList<>();
        return itemsList;
    }

    @Override
    public void update(RegistryElement element) throws RowNotFound {
        nullOrEmptyOperation();
        itemsList.remove(select(element.getCategoryID().toString()));
        insert(element);
        saveToFile();
    }

    @Override
    public void deleteByID(Object categoryId) throws RowNotFound {
        nullOrEmptyOperation();
        itemsList.remove(select(categoryId));
        saveToFile();
    }

    @Override
    public void delete(RegistryElement element) throws RowNotFound {
        nullOrEmptyOperation();
        itemsList.remove(element);
        saveToFile();
    }
    private void nullOrEmptyOperation() {
        if (itemsList == null || itemsList.size() == 0) {
            selectALL();
        }
    }
    private void saveToFile() {
        XmlMapper mapper = new XmlMapper();
        try {
            WarehouseRegistryListContainerToXml container = new WarehouseRegistryListContainerToXml();
            container.setDataList(itemsList);
            new FileProcessor().writeToFile(ApplicationProperties.MAIN_WAREHOUSE_REGISTRY_FILE_PATH,mapper.writeValueAsString(container));
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException at the UserDaoXML insert method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        } catch (IOException e) {
            logger.error("IOException at the UserDaoXML insert method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        }
    }

}
