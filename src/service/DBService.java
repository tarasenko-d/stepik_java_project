package service;

import dao.UsersDAO;
import model.User;

import java.util.Optional;

public class DBService implements DBServiceI {

    UsersDAO userDAO = new UsersDAO();

    @Override
    public void addOnlyUser(String login, String password) {
        Optional<User> userOptional = Optional.ofNullable(userDAO.findByName(login));
        System.out.println(userOptional);
        if (userOptional.isEmpty()) {
            userDAO.save(login, password);
            return;
        }
    }

    @Override
    public boolean doesntExists(String login){
        Optional<User> userOptional = Optional.ofNullable(userDAO.findByName(login));
        return userOptional.isEmpty();
    }

    //Возвращает true если юзер отсутствует и false, если существует
    @Override
    public boolean CheckUser(String login, String password) {
        Optional<User> userOptional = Optional.ofNullable(userDAO.correctAccount(login, password));
        return (userOptional.isEmpty());
    }

    @Override
    public void banUser(String login) {
        userDAO.banUser(login);
    }

    @Override
    public boolean isBanned(String login) {
        return userDAO.checkBan(login);
    }


    @Override
    public void deleteUser(String name, String password) {
        userDAO.delete(name, password);
    }

    @Override
    public void updateUser(String name, String password) {
        userDAO.update(name, password);
    }
}

