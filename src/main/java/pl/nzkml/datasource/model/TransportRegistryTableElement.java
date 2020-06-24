package pl.nzkml.datasource.model;

import java.util.Date;

public class TransportRegistryTableElement {
    String transportID;
    String date;
    Integer numberOfCategories;
    String isAcceptred;

    public String getTransportID() {
        return transportID;
    }

    public void setTransportID(String transportID) {
        this.transportID = transportID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNumberOfCategories() {
        return numberOfCategories;
    }

    public void setNumberOfCategories(Integer numberOfCategories) {
        this.numberOfCategories = numberOfCategories;
    }

    public String getIsAcceptred() {
        return isAcceptred;
    }

    public void setIsAcceptred(String isAcceptred) {
        this.isAcceptred = isAcceptred;
    }

    public TransportRegistryTableElement() {
    }

    public TransportRegistryTableElement(String transportID, String date, Integer numberOfCategories, String isAcceptred) {
        this.transportID = transportID;
        this.date = date;
        this.numberOfCategories = numberOfCategories;
        this.isAcceptred = isAcceptred;
    }
}
