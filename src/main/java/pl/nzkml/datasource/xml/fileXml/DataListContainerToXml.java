package pl.nzkml.datasource.xml.fileXml;

import java.util.List;

public class DataListContainerToXml<T> {
    List<T> dataList;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
