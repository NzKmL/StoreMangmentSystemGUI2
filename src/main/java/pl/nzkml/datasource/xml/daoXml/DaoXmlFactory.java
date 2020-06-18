package pl.nzkml.datasource.xml.daoXml;

import pl.nzkml.datasource.CrudDao;
import pl.nzkml.datasource.DaoFactory;
import pl.nzkml.datasource.DaoType;
import pl.nzkml.datasource.xml.daoXml.user.UserDaoXML;

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
        return null;

    }
}
