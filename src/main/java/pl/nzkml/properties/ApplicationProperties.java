package pl.nzkml.properties;

import pl.nzkml.datasource.DataSourceType;

import java.util.HashMap;

public class ApplicationProperties extends HashMap<String, Object> {

public static final String userFilePath = "src\\main\\resources\\filesDataSource\\User.xml";
public static final String categoryFilePath = "src\\main\\resources\\filesDataSource\\Category.xml";
public static final DataSourceType dataSourceType = DataSourceType.XML;
public static final String startFXMLFile = "mainMenuDemo";
public static final String MAIN_MENU = "mainMenuDemo";

    public static final String CATEGORY_LIST_WINDOW = "categoryListWindow";
    public static final String NEW_CATEGORY_WINDOW = "categoryWindow" ;
}
