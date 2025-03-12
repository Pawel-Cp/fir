package org.example.dao.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.example.dao.CrimeDao;
import org.example.exception.DataProcessingException;
import org.example.model.Crime;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CrimeDaoImpl implements CrimeDao {

    @Override
    public Crime add(Crime crime) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.INSTANCE.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(crime);
            transaction.commit();
            return crime;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert crime: " + crime + " to DB!", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Crime> findById(Long id) {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Query<Crime> crimeQuery = session.createQuery("FROM Crime c "
                    + " LEFT JOIN FETCH c.fir "
                    + " WHERE c.id = :id ", Crime.class);
            crimeQuery.setParameter("id", id);
            return crimeQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get crime with id: " + id, e);
        }
    }

    @Override
    public List<Crime> findAll() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Query<Crime> crimeQuery = session.createQuery("FROM Crime ", Crime.class);
            return crimeQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all crimes!", e);
        }
    }

    @Override
    public List<Crime> findByDate(LocalDate date) {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Query<Crime> crimeQuery = session.createQuery("FROM Crime c"
                    + " LEFT JOIN FETCH c.fir "
                    + " WHERE c.date = :date ", Crime.class);
            crimeQuery.setParameter("date", date);
            return crimeQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get crimes by date: " + date, e);
        }
    }

    @Override
    public List<Crime> findByDateRange(LocalDate startDate, LocalDate endDate) {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Query<Crime> crimeQuery = session.createQuery("FROM Crime c "
                    + " LEFT JOIN FETCH c.fir "
                    + " WHERE c.date BETWEEN :startDate AND :endDate ", Crime.class);
            crimeQuery.setParameter("startDate", startDate);
            crimeQuery.setParameter("endDate", endDate);
            return crimeQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get crime by dates", e);
        }
    }

    @Override
    public List<Crime> findByLocation(String location) {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {

        }
    }

    @Override
    public List<Crime> findByType(String type) {
        return null;
    }

    @Override
    public void update(Crime crime) {

    }

    @Override
    public void delete(Long id) {

    }
}
