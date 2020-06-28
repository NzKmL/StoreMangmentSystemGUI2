package pl.nzkml.datasource;

public enum MetricType {
    KG("KG"), M("M"), M2("M2"), M3("M3"),P("P");


    String labelKey;

    MetricType(String key){
        labelKey = key;
    }

    public String getLabelKey() {
        return labelKey;
    }

}
