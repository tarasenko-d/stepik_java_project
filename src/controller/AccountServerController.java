package controller;


import lombok.AllArgsConstructor;
import service.AccountServerI;

@AllArgsConstructor
public class AccountServerController implements AccountServerControllerMBean {
    private final AccountServerI accountServer;

    @Override
    public int getUsers() {
        return accountServer.getUsersCount();
    }

    @Override
    public int getUsersLimit() {
        return accountServer.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        accountServer.setUsersLimit(usersLimit);
    }
}





/*
    public AccountServerController(AccountServerI accountServer) {
        this.accountServer = accountServer;
    }

 */