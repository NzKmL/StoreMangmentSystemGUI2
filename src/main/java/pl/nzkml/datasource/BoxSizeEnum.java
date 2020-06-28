package pl.nzkml.datasource;

public enum BoxSizeEnum {
    SMALL("SMALL"), MEDIUM("MEDIUM"), LARGE("LARGE");


    String labelKey;

    BoxSizeEnum(String key){
        labelKey = key;
    }

    public String getLabelKey() {
        return labelKey;
    }

}
