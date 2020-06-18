package pl.nzkml.datasource.repository;

import pl.nzkml.datasource.CrudDao;
import pl.nzkml.datasource.Repository;
import pl.nzkml.datasource.repoException.RowNotFound;
import pl.nzkml.datasource.entity.users.User;

import java.util.List;

public class UserRepository implements Repository<User> {

    public UserRepository(CrudDao userDaoXML) {
        this.userDaoXML = userDaoXML;
    }

    CrudDao userDaoXML;

    @Override
    public void add(User element) {
    userDaoXML.insert(element);
    }

    @Override
    public List<User> getAllElements() {
        return userDaoXML.selectALL();
    }

    @Override
    public void remove(User element) throws RowNotFound {
        userDaoXML.delete(element);
    }

    @Override
    public void removeByID(String id) throws RowNotFound {
        userDaoXML.deleteByID(id);
    }

    @Override
    public User getByID(String id) {
        return (User) userDaoXML.select(id);
    }

    @Override
    public void update(User user) throws RowNotFound {
         userDaoXML.update(user);
    }
}
