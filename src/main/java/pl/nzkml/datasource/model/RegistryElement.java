package pl.nzkml.datasource.model;

public class RegistryElement {
    Integer categoryID;
    Integer numberOfItems;

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "RegistryElement{" +
                "categoryID=" + categoryID +
                ", numberOfItems=" + numberOfItems +
                '}';
    }
}
