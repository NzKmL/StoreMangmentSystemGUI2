package pl.nzkml.datasource.model.tableElement;

import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.Category;
import pl.nzkml.datasource.model.categoryListElement.OrderElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderElementTableElement {

    Integer lp;
    String categoryName;
    Integer quantity;
    Integer inventory;

    public OrderElementTableElement() {
    }

    public Integer getLp() {
        return lp;
    }

    public void setLp(Integer lp) {
        this.lp = lp;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "OrderElementTableElement{" +
                "lp=" + lp +
                ", categoryName='" + categoryName + '\'' +
                ", quantity=" + quantity +
                ", inventory='" + inventory + '\'' +
                '}';
    }
    public static List<OrderElementTableElement> getOrderElementTableElement( List<OrderElement> orderElements) {
      return  getOrderElementTableElement(orderElements, null);
    }

    public static List<OrderElementTableElement> getOrderElementTableElement(List<OrderElement> orderElements, Map<Integer, Integer> currentWarehouseStateMap) {
        List<OrderElementTableElement> list = new ArrayList<>();

        for (OrderElement orderElement : orderElements) {
            OrderElementTableElement orderElementTableElement = new OrderElementTableElement();
            orderElementTableElement.setCategoryName(((Category) RepositoryFactory.getInstance().createRepository(DataType.CATEGORY).getByID(String.valueOf(orderElement.getCategoryID()))).getName());
            orderElementTableElement.setLp(orderElement.getNo());
            orderElementTableElement.setQuantity(orderElement.getQuantity());
            if(currentWarehouseStateMap!=null) {
                orderElementTableElement.setInventory(currentWarehouseStateMap.get(orderElement.getCategoryID())==null?0:currentWarehouseStateMap.get(orderElement.getCategoryID()));
            }
            list.add(orderElementTableElement);
        }
        return list;
    }

}
