package pl.nzkml.datasource.model.tableElement;

import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.Category;

import java.util.ArrayList;
import java.util.List;

    public class TransportTableElement{
        int no;
        String categoryName;
        Integer quantity;

        public TransportTableElement() {
        }

        public TransportTableElement(int no, String categoryName, Integer quantity) {
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
        public static List<TransportTableElement> convertToTableElementList(List<TransportElement> list){

            List<TransportTableElement> newList = new ArrayList<>();
            for (TransportElement temp :list) {
                TransportTableElement element = new TransportTableElement(temp.getNo(),((Category) RepositoryFactory.getInstance().createRepository(DataType.CATEGORY).getByID(temp.getCategoryID().toString())).getName(), temp.getQuantity());
                newList.add(element);
            }
            return newList;
        }
    }

