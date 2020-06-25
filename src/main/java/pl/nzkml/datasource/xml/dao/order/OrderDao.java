package pl.nzkml.datasource.xml.dao.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.datasource.CrudDao;
import pl.nzkml.datasource.model.Order;
import pl.nzkml.datasource.repoException.RowNotFound;
import pl.nzkml.datasource.xml.file.FileProcessor;
import pl.nzkml.properties.ApplicationProperties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDao implements CrudDao<Order> {
    List<Order> orderList;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void insert(Order order) {
        nullOrEmptyOperation();
        orderList.add(order);
        saveToFile();
    }
    @Override
    public Order select(Object id) {
        if (id==null) return null;

        nullOrEmptyOperation();
        if (orderList.isEmpty()) {
            return null;
        }else{
            return orderList.stream().filter(temp -> (id).equals(temp.getOrderID())).findAny().orElse(null);
        }
    }

    @Override
    public List<Order> selectALL() {
        XmlMapper mapper = new XmlMapper();

        FileProcessor fileProcessor = new FileProcessor();
        String xml = null;
        try {
            xml = fileProcessor.readFile(ApplicationProperties.ORDER_FILE_PATH);
            if(!xml.isEmpty()) {
                OrderListContainerToXml orderListXmlContainer = new OrderListContainerToXml();
                orderListXmlContainer = mapper.readValue(xml, orderListXmlContainer.getClass());
                orderList =orderListXmlContainer.getDataList();
            } else {
                orderList = new ArrayList<>();
            }
        }
        catch (FileNotFoundException e) {
            logger.error("FileNotFoundException at the UserDaoXML selectALL method " + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException at the UserDaoXML selectALL method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        }
        if(orderList==null) orderList = new ArrayList<>();
        return orderList;
    }


    @Override
    public void update(Order order) throws RowNotFound {
        nullOrEmptyOperation();
        orderList.remove(select(order.getOrderID()));
        insert(order);
        saveToFile();
    }

    @Override
    public void deleteByID(Object id) throws RowNotFound {
        nullOrEmptyOperation();
        orderList.remove(select(id));
        saveToFile();
    }

    @Override
    public void delete(Order order) throws RowNotFound {
        nullOrEmptyOperation();
        orderList.remove(order);
        saveToFile();
    }
    private void nullOrEmptyOperation() {
        if (orderList == null || orderList.size() == 0) {
            selectALL();
        }
    }
    private void saveToFile() {
        XmlMapper mapper = new XmlMapper();
        try {
            OrderListContainerToXml container = new OrderListContainerToXml();
            container.setDataList(orderList);
            new FileProcessor().writeToFile(ApplicationProperties.ORDER_FILE_PATH,mapper.writeValueAsString(container));
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException at the TransportDao insert method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        } catch (IOException e) {
            logger.error("IOException at the TransportDao insert method " + e.getMessage());
            Arrays.stream(e.getStackTrace()).forEach(a -> logger.error(a.toString()));
        }
    }
}
