package pl.nzkml.datasource.model.tableElement;

import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.Category;
import pl.nzkml.datasource.model.categoryListElement.OrderElement;

import java.util.ArrayList;
import java.util.List;

public class OrderTableElements extends TransferTableElement{
    public OrderTableElements() {
    }

    public OrderTableElements(int no, String categoryName, Integer quantity) {
        super(no, categoryName, quantity);
    }

    public static List<OrderTableElements> convertToTableElementList(List<OrderElement> list){

        List<OrderTableElements> newList = new ArrayList<>();
        for (OrderElement temp :list) {
            OrderTableElements element = new OrderTableElements(temp.getNo(),((Category) RepositoryFactory.getInstance().createRepository(DataType.CATEGORY).getByID(temp.getCategoryID().toString())).getName(), temp.getQuantity());
            newList.add(element);
        }
        return newList;
    }
}
