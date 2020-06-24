package pl.nzkml.datasource.xml.dao.transport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.datasource.CrudDao;
import pl.nzkml.datasource.entity.Transport;
import pl.nzkml.datasource.repoException.RowNotFound;
import pl.nzkml.datasource.xml.file.FileProcessor;
import pl.nzkml.properties.ApplicationProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransportDao implements CrudDao<Transport> {
    List<Transport> transportList;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void insert(Transport transport) {
        nullOrEmptyOperation();
        transportList.add(transport);
        saveToFile();
    }
    @Override
    public Transport select(Object id) {
        if (id==null) return null;

        nullOrEmptyOperation();
        if (transportList.isEmpty()) {
            return null;
        }else{
            return transportList.stream().filter(temp -> (Integer.valueOf((String)id)).equals(temp.getTransportID())).findAny().orElse(null);
        }
    }

    @Override

    public List<Transport> selectALL() {
        XmlMapper mapper = new XmlMapper();

        FileProcessor fileProcessor = new FileProcessor();
        String xml = null;
        try {
            xml = fileProcessor.readFile(ApplicationProperties.TRANSPORT_FILE_PATH);
            if(!xml.isEmpty()) {
                TransportListContainerToXml categoryListXmlContainer = new TransportListContainerToXml();
                categoryListXmlContainer = mapper.readValue(xml, categoryListXmlContainer.getClass());
                transportList =categoryListXmlContainer.getDataList();
            } else {
                transportList = new ArrayList<>();
            }
        }
        catch (FileNotFoundException e) {
            logger.error("FileNotFoundException at the UserDaoXML selectALL method " + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException at the UserDaoXML selectALL method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        }

        return transportList;
    }


    @Override
    public void update(Transport transport) throws RowNotFound {
        nullOrEmptyOperation();
        transportList.remove(select(transport.getTransportID()));
        insert(transport);
        saveToFile();
    }

    @Override
    public void deleteByID(Object id) throws RowNotFound {
        nullOrEmptyOperation();
        transportList.remove(select(id));
        saveToFile();
    }

    @Override
    public void delete(Transport transport) throws RowNotFound {
        nullOrEmptyOperation();
        transportList.remove(transport);
        saveToFile();
    }
    private void nullOrEmptyOperation() {
        if (transportList == null || transportList.size() == 0) {
            selectALL();
        }
    }
    private void saveToFile() {
        XmlMapper mapper = new XmlMapper();
        try {
            TransportListContainerToXml container = new TransportListContainerToXml();
            container.setDataList(transportList);
            new FileProcessor().writeToFile(ApplicationProperties.TRANSPORT_FILE_PATH,mapper.writeValueAsString(container));
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException at the TransportDao insert method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        } catch (IOException e) {
            logger.error("IOException at the TransportDao insert method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        }
    }
}
