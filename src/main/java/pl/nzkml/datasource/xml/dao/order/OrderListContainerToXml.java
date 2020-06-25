package pl.nzkml.datasource.xml.dao.order;

import pl.nzkml.datasource.model.Order;

import java.util.List;

public class OrderListContainerToXml {
    List<Order> dataList;

    public List<Order> getDataList() {
        return dataList;
    }

    public void setDataList(List<Order> dataList) {
        this.dataList = dataList;
    }
}
