package pl.nzkml.datasource.model.tableElement;

public abstract class TransferTableElement {
    int no;
    String categoryName;
    Integer quantity;

    public TransferTableElement() {
    }

    public TransferTableElement(int no, String categoryName, Integer quantity) {
        this.no = no;
        this.categoryName = categoryName;
        this.quantity = quantity;
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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
