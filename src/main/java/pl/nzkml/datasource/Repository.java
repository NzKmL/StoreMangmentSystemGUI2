package pl.nzkml.datasource;

import pl.nzkml.datasource.repoException.RowNotFound;

import java.util.List;

public interface Repository<T> {
    public void add(T element);
    public List<T> getAllElements();
    public void remove(T element) throws RowNotFound;
    public void removeByID(String id) throws RowNotFound;
    public T getByID(String id);
    public void update(T element) throws RowNotFound;
}
