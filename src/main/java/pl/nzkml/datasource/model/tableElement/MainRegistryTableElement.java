package pl.nzkml.datasource.model.tableElement;

public class MainRegistryTableElement {
    String categoryName;
    Integer numberOfItems;

    public MainRegistryTableElement() {
    }

    public MainRegistryTableElement(String categoryName, Integer numberOfItems) {
        this.categoryName = categoryName;
        this.numberOfItems = numberOfItems;
    }



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    @Override
    public String toString() {
        return "MainRegistryTableElement{" +
                "categoryName='" + categoryName + '\'' +
                ", numberOfItems=" + numberOfItems +
                '}';
    }
}
