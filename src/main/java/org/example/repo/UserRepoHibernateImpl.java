package org.example.repo;

import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class UserRepoHibernateImpl implements UserRepo {

    private final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    @Override
    public void save(User user) {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void remove(User user) {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            List<User> users = session.createQuery("SELECT u FROM User u", User.class).getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public Optional<User> logInByUserNameAndPassword(String name, String password) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                            User.class).setParameter("username", name).setParameter("password", password)
                            .getSingleResult();
            session.getTransaction().commit();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUserName(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.createQuery("SELECT u FROM User u Where  u.username = :username",
                            User.class).setParameter("username", name)
                            .getSingleResult();
            session.getTransaction().commit();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void close() throws Exception {
        factory.close();
    }
}
