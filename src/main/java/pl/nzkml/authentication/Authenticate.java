package pl.nzkml.authentication;

public interface Authenticate {
    public boolean isPasswordCorrect(String login, String password);
    public boolean isPasswordExpired(String login, String password);
}
