package org.example.dao;

import java.util.List;
import java.util.Optional;
import org.example.model.Complaint;
import org.example.model.User;

public interface ComplaintDao {

    Complaint add(Complaint complaint);

    Optional<Complaint> findById(Long id);

    List<Complaint> findAll();

    Optional<Complaint> findByUser(User user);

    void delete(Complaint complaint);

}
