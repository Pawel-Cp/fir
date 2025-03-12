package org.example.dao;

import org.example.model.Complaint;

public interface ComplaintDao {

    Complaint add(Complaint complaint);

    Complaint findById(Long id);

    List<Complaint> findAll();

    Complaint findByUser(User user);

    boolean updateStatus(Complaint complaint, Complaint.Status newStatus);

    void delete(Long id);

    Complaint assignFirToComplaint(Long complaintId, Fir fir);

    Complaint removeFir(Long firId);
}
