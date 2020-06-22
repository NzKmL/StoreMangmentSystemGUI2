package pl.nzkml.datasource.WS.repository;

import pl.nzkml.datasource.Repository;
import pl.nzkml.datasource.repoException.RowNotFound;

import java.util.List;

public class RepositoryWS<T> implements Repository<T> {

    @Override
    public void add(T element) {
        //TODO Tu kiedyś będzie wywołanie
    }

    @Override
    public List<T> getAllElements() {
        //TODO Tu kiedyś będzie webservice
        return null;
    }

    @Override
    public void remove(T element) throws RowNotFound {
    //TODO Tu kiedyś będzie webservice
    }

    @Override
    public void removeByID(String id) throws RowNotFound {
    //TODO Tu kiedyś będzie webservice
    }

    @Override
    public T getByID(String id) {
        //TODO Tu kiedyś będzie webservice
        return null;
    }

    @Override
    public void update(T element) throws RowNotFound {
    //TODO Tu kiedyś będzie webservice
    }
}
