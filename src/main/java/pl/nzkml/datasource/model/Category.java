package pl.nzkml.datasource.model;

import pl.nzkml.datasource.BoxSizeEnum;

public class Category {
    Integer id;
    String name;
    String metric;
    Integer boxQuantity;
    BoxSizeEnum boxSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Integer getBoxQuantity() {
        return boxQuantity;
    }

    public void setBoxQuantity(Integer boxQuantity) {
        this.boxQuantity = boxQuantity;
    }

    public BoxSizeEnum getBoxSize() {
        return boxSize;
    }

    public void setBoxSize(BoxSizeEnum boxSize) {
        this.boxSize = boxSize;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", metric='" + metric + '\'' +
                ", boxQuantity=" + boxQuantity +
                ", boxSize='" + boxSize + '\'' +
                '}';
    }
}
