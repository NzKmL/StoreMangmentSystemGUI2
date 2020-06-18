package pl.nzkml.authentication;

import pl.nzkml.datasource.DaoFactory;
import pl.nzkml.datasource.DaoType;
import pl.nzkml.datasource.Repository;
import pl.nzkml.datasource.entity.users.User;
import pl.nzkml.datasource.repository.UserRepository;
import pl.nzkml.datasource.xml.daoXml.DaoXmlFactory;

public class AuthenticationService implements Authenticate {
    @Override
    public boolean isPasswordCorrect(String login, String password) {



        if (login ==null || password==null) return false;

        DaoFactory factory = DaoXmlFactory.getInstance();
        Repository repo = new UserRepository(factory.createDao(DaoType.USER));
        User user = (User) repo.getByID(login);

        if(isPasswordExpired(login, password)){
            return false;
        };


        if (login.equals(user.getLogin()) && password.equals(user.getPassword())){
            return true;
        }
        else return false;

    }

    @Override
    public boolean isPasswordExpired(String login, String password) {
        //TODO: dorobić gdy powstanie repo dla użytkowników
        return false;
    }
}
