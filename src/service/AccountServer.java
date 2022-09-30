package service;

import java.util.HashSet;
import java.util.Set;

public class AccountServer implements AccountServerI {
    private int usersCount;
    private int usersLimit;
    private Set<String> usersOnline;
    public AccountServer(int usersLimit) {
        this.usersCount = 0;
        this.usersLimit = usersLimit;
        this.usersOnline = new HashSet<>();
    }

    @Override
    public void addNewUser(String login) {
        usersCount += 1;
        usersOnline.add(login);
        System.out.println("Add user to onlineList");
    }

    @Override
    public boolean isOnline(String login){
        return usersOnline.contains(login);
    }

    @Override
    public void removeUser(String login) {
        usersCount -= 1;
        usersOnline.remove(login);
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    @Override
    public int getUsersCount() {
        return usersCount;
    }
}
