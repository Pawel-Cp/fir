package org.example.dao.impl;

import org.example.dao.ComplaintDao;
import org.example.exception.DataProcessingException;
import org.example.lib.Dao;
import org.example.model.Complaint;
import org.example.model.Fir;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@Dao
public class ComplaintDaoImpl implements ComplaintDao {

    @Override
    public Complaint add(Complaint complaint) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.INSTANCE.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(complaint);
            transaction.commit();
            return complaint;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert complaint to the DB!", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Complaint findById(Long id) {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Query<Complaint> complaintQuery = session.createQuery("FROM Complaint c "
                    + " LEFT JOIN FETCH c.fir f "
                    + " LEFT JOIN FETCH f.crime cr "
                    + " WHERE c.id = :id ", Complaint.class);
            complaintQuery.setParameter("id", id);
            return complaintQuery.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get complaint with id: " + id, e);
        }
    }

    @Override
    public List<Complaint> findAll() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Query<Complaint> complaintQuery = session.createQuery("FROM Complaint", Complaint.class);
            return complaintQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get list of all complaints!", e);
        }
    }

    @Override
    public boolean updateStatus(Long id, Complaint.Status newStatus) {
        Complaint complaintToUpdate = findById(id);
        Session session = null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Complaint assignFirToComplaint(Long complaintId, Fir fir) {
        return null;
    }

    @Override
    public void removeFir(Long firId) {

    }
}
