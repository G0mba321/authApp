package org.example.connection;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {

    @Getter
    private static SessionFactory sessionFactory;

    public static void initConnection() {
        sessionFactory = buildSessionFactory();
    }

    private static SessionFactory buildSessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void close() {
        getSessionFactory().close();
    }
}
