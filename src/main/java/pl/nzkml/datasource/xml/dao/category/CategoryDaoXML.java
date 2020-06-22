package pl.nzkml.datasource.xml.dao.category;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.datasource.CrudDao;
import pl.nzkml.datasource.entity.Category;
import pl.nzkml.datasource.entity.User;
import pl.nzkml.datasource.repoException.RowNotFound;
import pl.nzkml.datasource.xml.file.FileProcessor;
import pl.nzkml.properties.ApplicationProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryDaoXML implements CrudDao<Category> {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void insert(Category user) {
        nullOrEmptyOperation();
        categoryList.add(user);
        saveToFile();
    }
    @Override
    public Category select(Object id) {
        if (id==null) return null;

        nullOrEmptyOperation();
        if (categoryList.isEmpty()) {
            return null;
        }else{
            return categoryList.stream().filter(temp -> (String.valueOf(id)).equals(temp.getId())).findAny().orElse(null);
        }
    }
private List<Category> categoryList;
    @Override
    public List<Category> selectALL() {
        XmlMapper mapper = new XmlMapper();

        FileProcessor fileProcessor = new FileProcessor();
        String xml = null;
        try {
            xml = fileProcessor.readFile(ApplicationProperties.categoryFilePath);
           if(!xml.isEmpty()) {
               CategoryListContainerToXml categoryListXmlContainer = new CategoryListContainerToXml();
                categoryListXmlContainer = mapper.readValue(xml, categoryListXmlContainer.getClass());
               categoryList =categoryListXmlContainer.getDataList();
           } else {
               categoryList = new ArrayList<>();
           }
        }
        catch (FileNotFoundException e) {
            logger.error("FileNotFoundException at the UserDaoXML selectALL method " + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException at the UserDaoXML selectALL method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        }

        return categoryList;
    }

    @Override
    public void update(Category category) throws RowNotFound {
        nullOrEmptyOperation();
        categoryList.remove(select(category.getId()));
        insert(category);
        saveToFile();
    }

    @Override
    public void deleteByID(Object id) throws RowNotFound {
        nullOrEmptyOperation();
        categoryList.remove(select(id));
        saveToFile();
    }

    @Override
    public void delete(Category categoty) throws RowNotFound {
        nullOrEmptyOperation();
        categoryList.remove(categoty);
        saveToFile();
    }
    private void nullOrEmptyOperation() {
        if (categoryList == null || categoryList.size() == 0) {
            selectALL();
        }
    }
    private void saveToFile() {
        XmlMapper mapper = new XmlMapper();
        try {
            CategoryListContainerToXml container = new CategoryListContainerToXml();
            container.setDataList(categoryList);
            new FileProcessor().writeToFile(ApplicationProperties.categoryFilePath,mapper.writeValueAsString(container));
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException at the UserDaoXML insert method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        } catch (IOException e) {
            logger.error("IOException at the UserDaoXML insert method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        }
    }

}