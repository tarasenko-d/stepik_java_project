package service;

public interface AccountServerI {


        void addNewUser(String info);

        boolean isOnline(String login);

        void removeUser(String login);

        int getUsersLimit();

        void setUsersLimit(int usersLimit);

        int getUsersCount();


}
