package org.example.dao;

import org.example.model.Complaint;
import org.example.model.Fir;

import java.util.List;

public interface ComplaintDao {

    Complaint add(Complaint complaint);

    Complaint findById(Long id);

    List<Complaint> findAll();

    boolean updateStatus(Long id, Complaint.Status newStatus);

    void delete(Long id);

    Complaint assignFirToComplaint(Long complaintId, Fir fir);

    void removeFir(Long firId);

}
