package pl.nzkml.datasource;

import pl.nzkml.datasource.repoException.RowNotFound;

import java.util.List;

public interface CrudDao<T> {
    public void insert(T t);
    public T select(Object id);
    public List<T> selectALL();
    public void update(T t) throws RowNotFound;
    public void deleteByID(Object id) throws RowNotFound;
    public void delete(T t) throws RowNotFound;

}
