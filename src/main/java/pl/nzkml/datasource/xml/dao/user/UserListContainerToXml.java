package pl.nzkml.datasource.xml.dao.user;

import pl.nzkml.datasource.model.User;

import java.util.List;

public class UserListContainerToXml {
    List<User> dataList;

    public List<User> getDataList() {
        return dataList;
    }

    public void setDataList(List<User> dataList) {
        this.dataList = dataList;
    }
}


