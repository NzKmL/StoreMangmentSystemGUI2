package pl.nzkml.properties;

import pl.nzkml.datasource.DataSourceType;

import java.util.HashMap;

public class ApplicationProperties extends HashMap<String, Object> {

public static final String USER_FILE_PATH = "src\\main\\resources\\filesDataSource\\User.xml";
public static final String CATEGORY_FILE_PATH = "src\\main\\resources\\filesDataSource\\Category.xml";
public static final String TRANSPORT_FILE_PATH = "src\\main\\resources\\filesDataSource\\Transport.xml";
public static final DataSourceType DATE_SOURCE_TYPE = DataSourceType.XML;
public static final String START_FXML_FILE = "mainMenuDemo";
public static final String MAIN_MENU = "mainMenuDemo";

    public static final String CATEGORY_LIST_WINDOW = "categoryListWindow";
    public static final String NEW_CATEGORY_WINDOW = "categoryWindow" ;
    public static final String ADD_TRANPOSRT_WINDOW = "loadTransportWindow";

}
