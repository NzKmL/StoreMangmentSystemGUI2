package pl.nzkml.authentication;

import pl.nzkml.datasource.DataType;
import pl.nzkml.datasource.Repository;
import pl.nzkml.datasource.RepositoryFactory;
import pl.nzkml.datasource.model.User;

public class AuthenticationService implements Authenticate {
    @Override
    public boolean isPasswordCorrect(String login, String password) {



        if (login ==null || password==null) return false;

        Repository repo = RepositoryFactory.getInstance().createRepository(DataType.USER);
        User user = (User) repo.getByID(login);
        if(user == null){
            return false;
        }
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
