package pl.nzkml.datasource.model;

import pl.nzkml.datasource.model.categoryListElement.OrderElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    String orderID;
    Date orderDate;
    Boolean realized;
    List<OrderElement> orderElement = new ArrayList<>();

    public Order() {
    }

    public Order(String orderID, Date orderDate, Boolean realized, List<OrderElement> orderElement) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.realized = realized;
        this.orderElement = orderElement;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getRealized() {
        return realized;
    }

    public void setRealized(Boolean realized) {
        this.realized = realized;
    }

    public List<OrderElement> getOrderElement() {
        return orderElement;
    }

    public void setOrderElement(List<OrderElement> orderElement) {
        this.orderElement = orderElement;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", orderDate=" + orderDate +
                ", realized=" + realized +
                ", orderElement=" + orderElement +
                '}';
    }
}
