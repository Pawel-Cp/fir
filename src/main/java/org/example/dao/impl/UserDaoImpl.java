package org.example.dao.impl;

import org.example.dao.UserDao;
import org.example.exception.DataProcessingException;
import org.example.model.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public User add(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.INSTANCE.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add user to the DB!", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Query<User> userQuery = session.createQuery("FROM User u "
                    + " WHERE u.username = :username", User.class);
            userQuery.setParameter("username", username);
            return userQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("There is some problems with DB connection!", e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Query<User> userQuery = session.createQuery("FROM User u "
                    + " WHERE u.id = :id ", User.class);
            userQuery.setParameter("id", id);
            return userQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("There is some problems with DB connection!", e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Query<User> usersQuery = session.createQuery("FROM User", User.class);
            return usersQuery.getResultList();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        Transaction transaction= null;
        try {
            session = HibernateUtil.INSTANCE.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(findById(id).orElseThrow(
                    () -> new RuntimeException("User with specified id: " + id + "doesn't exist!")));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't remove user from DB with id: " + id, e);
        }
    }

    @Override
    public boolean update(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.INSTANCE.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update user: " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
