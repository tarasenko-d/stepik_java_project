package factory;


import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import static config.DBConfig.getH2Configuration;

public class SessionFactoryFactory {
    private static SessionFactory sessionFactory;

    private SessionFactoryFactory() {
    }

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = getH2Configuration();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

}