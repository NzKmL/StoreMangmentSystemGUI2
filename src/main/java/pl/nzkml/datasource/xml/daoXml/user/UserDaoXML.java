package pl.nzkml.datasource.xml.daoXml.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.datasource.xml.fileXml.DataListContainerToXml;
import pl.nzkml.datasource.xml.fileXml.FileProcessor;

import pl.nzkml.datasource.CrudDao;
import pl.nzkml.datasource.repoException.RowNotFound;
import pl.nzkml.datasource.entity.users.User;
import pl.nzkml.datasource.xml.fileXml.UserListContainerToXml;
import pl.nzkml.properties.ApplicationProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoXML implements CrudDao<User> {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void insert(User user) {
        nullOrEmptyOperation();
        userList.add(user);
        saveToFile();
    }
    @Override
    public User select(Object login) {
        if (login==null) return null;

        nullOrEmptyOperation();
        if (userList.isEmpty()) {
            return null;
        }else{
            return userList.stream().filter(temp -> (String.valueOf(login)).equals(temp.getLogin())).findAny().orElse(null);
        }
    }
private List<User> userList;
    @Override
    public List<User> selectALL() {
        XmlMapper mapper = new XmlMapper();

        FileProcessor fileProcessor = new FileProcessor();
        String xml = null;
        try {
            xml = fileProcessor.readFile(ApplicationProperties.userFilePath);
           if(!xml.isEmpty()) {
               UserListContainerToXml userListXmlContainer = new UserListContainerToXml();
                userListXmlContainer = mapper.readValue(xml, userListXmlContainer.getClass());
               userList=userListXmlContainer.getDataList();
           } else {
               userList = new ArrayList<>();
           }
        }
        catch (FileNotFoundException e) {
            logger.error("FileNotFoundException at the UserDaoXML selectALL method " + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException at the UserDaoXML selectALL method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        }

        return userList;
    }

    @Override
    public void update(User user) throws RowNotFound {
        nullOrEmptyOperation();
        userList.remove(select(user.getLogin()));
        insert(user);
        saveToFile();
    }

    @Override
    public void deleteByID(Object id) throws RowNotFound {
        nullOrEmptyOperation();
        userList.remove(select(id));
        saveToFile();
    }

    @Override
    public void delete(User user) throws RowNotFound {
        nullOrEmptyOperation();
        userList.remove(user);
        saveToFile();
    }
    private void nullOrEmptyOperation() {
        if (userList == null || userList.size() == 0) {
            selectALL();
        }
    }
    private void saveToFile() {
        XmlMapper mapper = new XmlMapper();
        try {
            DataListContainerToXml<User> container = new  DataListContainerToXml();
            container.setDataList(userList);
            new FileProcessor().writeToFile(ApplicationProperties.userFilePath,mapper.writeValueAsString(container));
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException at the UserDaoXML insert method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        } catch (IOException e) {
            logger.error("IOException at the UserDaoXML insert method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        }
    }

}
