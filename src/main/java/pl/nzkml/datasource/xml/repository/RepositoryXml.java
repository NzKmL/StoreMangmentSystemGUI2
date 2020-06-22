package pl.nzkml.datasource.xml.repository;

import pl.nzkml.datasource.CrudDao;
import pl.nzkml.datasource.Repository;
import pl.nzkml.datasource.repoException.RowNotFound;

import java.util.List;

public class RepositoryXml<T> implements Repository<T> {

    public RepositoryXml(CrudDao crudDaoXML) {
        this.crudDaoXML = crudDaoXML;
    }

    CrudDao crudDaoXML;

    @Override
    public void add(T element) {
    crudDaoXML.insert(element);
    }

    @Override
    public List<T> getAllElements() {
        return crudDaoXML.selectALL();
    }

    @Override
    public void remove(T element) throws RowNotFound {
        crudDaoXML.delete(element);
    }

    @Override
    public void removeByID(String id) throws RowNotFound {
        crudDaoXML.deleteByID(id);
    }

    @Override
    public T getByID(String id) {
        return (T) crudDaoXML.select(id);
    }

    @Override
    public void update(T user) throws RowNotFound {
         crudDaoXML.update(user);
    }
}
