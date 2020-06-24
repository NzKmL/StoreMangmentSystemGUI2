package pl.nzkml.datasource.xml.dao;

import pl.nzkml.datasource.CrudDao;
import pl.nzkml.datasource.DaoFactory;
import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.DataSourceType;
import pl.nzkml.datasource.xml.dao.category.CategoryDaoXML;
import pl.nzkml.datasource.xml.dao.transport.TransportDao;
import pl.nzkml.datasource.xml.dao.user.UserDaoXML;
import pl.nzkml.properties.ApplicationProperties;

public class DaoXmlFactory implements DaoFactory {

    private static DaoFactory instance;

    public static DaoFactory getInstance(){
        if (instance==null) instance = new DaoXmlFactory();
        return instance;
    }

    public CrudDao createDao(DataType type){
    if(DataSourceType.WS == ApplicationProperties.DATE_SOURCE_TYPE){
        //TODO DAO factory for WS
    }
        if(type.equals(DataType.USER)){
            return new UserDaoXML();
        }
        else if(type.equals(DataType.CATEGORY)){
            return new CategoryDaoXML();
        }
        else if(type.equals(DataType.TRANSPORT)){
            return new TransportDao();
        }
        return null;

    }
}
