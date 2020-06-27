package pl.nzkml.datasource.xml.dao.mainRegistry;

import pl.nzkml.datasource.model.RegistryElement;
import pl.nzkml.datasource.model.User;

import java.util.List;

public class WarehouseRegistryListContainerToXml {
    List<RegistryElement> dataList;

    public List<RegistryElement> getDataList() {
        return dataList;
    }

    public void setDataList(List<RegistryElement> dataList) {
        this.dataList = dataList;
    }
}


