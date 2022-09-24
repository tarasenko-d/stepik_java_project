package service;

import org.hibernate.HibernateException;

public class DBException extends Exception{

    public DBException(Throwable throwable) {
        super(throwable);
    }

}
