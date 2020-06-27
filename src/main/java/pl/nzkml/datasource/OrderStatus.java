package pl.nzkml.datasource;

public enum OrderStatus {
    REALIZED("realized" ), NOT_REALIZED("notRealized_possble"), NOT_ENAUGHT("notRealized_inposible");

    String labelKey;

    private OrderStatus(String key){
        labelKey = key;
    }

    public String getLabelKey() {
        return labelKey;
    }
}
