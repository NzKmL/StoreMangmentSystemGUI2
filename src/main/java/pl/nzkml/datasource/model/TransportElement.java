package pl.nzkml.datasource.model;

public class TransportElement {
        int no;
        Integer categoryID;
        Integer quantity;

    public TransportElement() {
    }

    public TransportElement(int no, Integer categoryID, Integer quantity) {
        this.no = no;
        this.categoryID = categoryID;
        this.quantity = quantity;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "TransportElement{" +
                "no=" + no +
                ", categoryID=" + categoryID +
                ", quantity=" + quantity +
                '}';
    }
}

