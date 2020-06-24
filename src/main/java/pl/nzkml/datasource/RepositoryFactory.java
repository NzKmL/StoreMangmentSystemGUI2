package pl.nzkml.datasource;

import pl.nzkml.datasource.WS.repository.RepositoryWS;
import pl.nzkml.datasource.xml.dao.DaoXmlFactory;
import pl.nzkml.datasource.xml.repository.RepositoryXml;
import pl.nzkml.properties.ApplicationProperties;



public class RepositoryFactory {
    private static RepositoryFactory instance;

    public static RepositoryFactory getInstance() {
        if (instance == null) instance = new RepositoryFactory();
        return instance;
    }

    public Repository createRepository(DataType type) {
        if (ApplicationProperties.DATE_SOURCE_TYPE == DataSourceType.WS) {
            //TODO: Do zrobienia przy implementacji WS
            switch (type) {

                    case USER:
                        return new RepositoryWS();
            }
        }
        else if(ApplicationProperties.DATE_SOURCE_TYPE == DataSourceType.XML){
                return new RepositoryXml(DaoXmlFactory.getInstance().createDao(type));
        }
    return null;
    }
}
