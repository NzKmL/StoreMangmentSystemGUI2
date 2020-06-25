package pl.nzkml.datasource.model.tableElement;

import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.Category;

import java.util.ArrayList;
import java.util.List;

    public class TransportTableElement extends TransferTableElement{
        public TransportTableElement() {
        }

        public TransportTableElement(int no, String categoryName, Integer quantity) {
            super(no, categoryName, quantity);
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

