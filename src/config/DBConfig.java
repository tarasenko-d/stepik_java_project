package config;

import model.User;
import org.hibernate.cfg.Configuration;

public class DBConfig {



    private static final String hibernate_show_sql = "true";
    private static final String hibernate_connection_username = "test";
    private static final String hibernate_connection_password = "test";
    private static final String hibernate_connection_url = "jdbc:h2:./h2db";
    //private static final String hibernate_connection_url = "jdbc:h2:test";
    private static final String hibernate_hbm2ddl_auto = "create";

    public static Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", hibernate_connection_url);
        configuration.setProperty("hibernate.connection.username", hibernate_connection_username);
        configuration.setProperty("hibernate.connection.password", hibernate_connection_password);
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);

        return configuration;
    }
}

