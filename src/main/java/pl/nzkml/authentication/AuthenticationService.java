package pl.nzkml.authentication;

public class AuthenticationService implements Authenticate {
    @Override
    public boolean isPasswordCorrect(String login, String password) {
        String loginAuth = "admin";
        String passAuth = "admin";

        //TODO: Serwis zewnętrzny / wewnętrzny do sprawdzania.
        //TODO: HashPassword
        if (loginAuth.equals(login) && passAuth.equals(password)){
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
