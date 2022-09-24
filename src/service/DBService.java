package service;

import dao.UsersDAO;
import model.User;

import java.util.Optional;

public class DBService {

    UsersDAO userDAO = new UsersDAO();


    public void addOnlyUser (String login, String password){
        Optional<User> userOptional = Optional.ofNullable(userDAO.findByName(login));
        System.out.println(userOptional);
        if (userOptional.isEmpty()){
            userDAO.save(login, password);
            return;
        }
    }

    public void banUser (String login){
        userDAO.banUser(login);
    }

    public boolean isBanned(String login){
        return userDAO.checkBan(login);
    }

    public boolean CheckUser (String login, String password){
        Optional<User> userOptional = Optional.ofNullable(userDAO.correctAccount(login,password));
        return (userOptional.isEmpty());
    }

    public User addUser(String login, String password) {
        return userDAO.save(login, password);
    }

    public void deleteUser(String name, String password) {
        userDAO.delete(name, password);
    }

    public void updateUser(String name, String password) {
        userDAO.update(name, password);
    }
}
