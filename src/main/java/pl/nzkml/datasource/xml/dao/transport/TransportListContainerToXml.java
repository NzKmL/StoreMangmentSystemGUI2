package pl.nzkml.datasource.xml.dao.transport;

import pl.nzkml.datasource.entity.Transport;
import pl.nzkml.datasource.entity.User;

import java.util.List;

public class TransportListContainerToXml {
    List<Transport> dataList;

    public List<Transport> getDataList() {
        return dataList;
    }

    public void setDataList(List<Transport> dataList) {
        this.dataList = dataList;
    }
}
