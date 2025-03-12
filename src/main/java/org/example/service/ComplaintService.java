package org.example.service;

import org.example.model.Complaint;
import org.example.model.Fir;

public interface ComplaintService {

    boolean updateStatus(Long id, Complaint.Status newStatus);

    Complaint assignFirToComplaint(Long complaintId, Fir fir);

    Complaint removeFir(Long firId);
}
