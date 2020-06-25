package pl.nzkml.datasource.xml.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nzkml.controllers.TransportRegistryController;
import pl.nzkml.datasource.CrudDao;
import pl.nzkml.datasource.DaoFactory;
import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.DataSourceType;
import pl.nzkml.datasource.xml.dao.category.CategoryDao;
import pl.nzkml.datasource.xml.dao.mainRegistry.MainRegistryDao;
import pl.nzkml.datasource.xml.dao.transport.TransportDao;
import pl.nzkml.datasource.xml.dao.user.UserDao;
import pl.nzkml.properties.ApplicationProperties;

public class DaoXmlFactory implements DaoFactory {
    private static final Logger logger = LoggerFactory.getLogger(TransportRegistryController.class);

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
            return new UserDao();
        }
        else if(type.equals(DataType.CATEGORY)){
            return new CategoryDao();
        }
        else if(type.equals(DataType.TRANSPORT)){
            return new TransportDao();
        }
        else if(type.equals(DataType.MAIN_REGISTRY)){
            return new MainRegistryDao();
        }
        else{
            logger.error("Dao not found for the data type enum");
        }

        return null;

    }
}
