package pl.nzkml.datasource.xml.dao;

import pl.nzkml.datasource.CrudDao;
import pl.nzkml.datasource.DaoFactory;
import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.DataSourceType;
import pl.nzkml.datasource.xml.dao.category.CategoryDaoXML;
import pl.nzkml.datasource.xml.dao.user.UserDaoXML;
import pl.nzkml.properties.ApplicationProperties;

public class DaoXmlFactory implements DaoFactory {

    private static DaoFactory instance;

    public static DaoFactory getInstance(){
        if (instance==null) instance = new DaoXmlFactory();
        return instance;
    }

    public CrudDao createDao(DaoType type){

        if(type.equals(DaoType.USER)){
            return new UserDaoXML();
        }
        else if(type.equals(DataType.CATEGORY)){
            return new CategoryDaoXML();
        }
        return null;

    }
}
