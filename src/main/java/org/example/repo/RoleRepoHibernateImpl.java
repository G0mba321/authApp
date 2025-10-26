package org.example.repo;

import org.example.connection.HibernateConnection;
import org.example.entity.Role;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class RoleRepoHibernateImpl implements RoleRepo {


    @Override
    public void save(Role role) {
        try (Session session = HibernateConnection.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.persist(role);
            session.getTransaction().commit();
        }
    }

    @Override
    public void remove(Role role) {
        try (Session session = HibernateConnection.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.remove(role);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Role> findAll() {
        try (Session session = HibernateConnection.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Role> roles = session.createQuery("SELECT r FROM Role r", Role.class).getResultList();
            session.getTransaction().commit();
            return roles;
        }
    }

    @Override
    public Optional<Role> findRoleName(String name) {
        try (Session session = HibernateConnection.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Role role = session.createQuery("SELECT r FROM Role r Where  r.name = :name",
                            Role.class).setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.ofNullable(role);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
