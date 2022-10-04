package service;

public interface DBServiceI {

    void addOnlyUser (String login, String password);

    boolean CheckUser (String login, String password);

    void banUser (String login);

    boolean isBanned(String login);

    void deleteUser(String name, String password);

    void updateUser(String name, String password);
}
