package dao;

import factory.SessionFactoryFactory;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UsersDAO {

    public User findByName(String name) {
        Session session = SessionFactoryFactory.createSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("name", name));
        User profile = (User) criteria.uniqueResult();
        session.close();
        return profile;
    }

    public User correctAccount(String name, String password) {
        Session session = SessionFactoryFactory.createSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class).
                add(Restrictions.eq("name", name)).
                add(Restrictions.eq("password", password));
        User profile = (User) criteria.uniqueResult();
        System.out.println(profile);
        session.close();
        return profile;
    }

    public User save(String name, String password) {
        Session session = SessionFactoryFactory.createSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = new User(name, password);
        session.save(user);
        System.out.println(user);
        tx1.commit();
        session.close();
        return user;
    }

    public User update(String name, String password) {
        Session session = SessionFactoryFactory.createSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = new User(name, password);
        user.setPassword(password);
        System.out.println(user);
        session.update(user);
        tx1.commit();
        session.close();
        return user;
    }

    public void delete(String name, String password) {
        Session session = SessionFactoryFactory.createSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = new User(name, password);
        System.out.println(user);
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public boolean checkBan(String name){
        Session session = SessionFactoryFactory.createSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("name", name));
        User profile = (User) criteria.uniqueResult();
        boolean isBanned =  profile.isBan();
        session.close();
        return isBanned;
    }

    public void banUser(String name){
        Session session = SessionFactoryFactory.createSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("name", name));
        User profile = (User) criteria.uniqueResult();
        profile.makeBan();
        tx1.commit();
        session.close();
    }

}
