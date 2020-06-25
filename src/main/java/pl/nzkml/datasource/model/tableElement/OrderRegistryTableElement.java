package pl.nzkml.datasource.model.tableElement;

public class OrderRegistryTableElement {
    String orderID;
    String date;
    Integer numberOfCategories;
    String status;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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

    public OrderRegistryTableElement(String orderID, String date, Integer numberOfCategories, String status) {
        this.orderID = orderID;
        this.date = date;
        this.numberOfCategories = numberOfCategories;
        this.status = status;
    }

    public OrderRegistryTableElement() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderRegistryTableElement{" +
                "transportID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                ", numberOfCategories=" + numberOfCategories +
                ", status='" + status + '\'' +
                '}';
    }
}
