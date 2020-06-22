package pl.nzkml.datasource.xml.dao.category;

import pl.nzkml.datasource.entity.Category;


import java.util.List;

public class CategoryListContainerToXml {
    List<Category> dataList;

    public List<Category> getDataList() {
        return dataList;
    }

    public void setDataList(List<Category> dataList) {
        this.dataList = dataList;
    }
}


