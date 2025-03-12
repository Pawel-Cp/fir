package org.example.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.example.model.Crime;

public interface CrimeDao {

    Crime add(Crime crime);

    Optional<Crime> findById(Long id);

    List<Crime> findAll();

    List<Crime> findByDate(LocalDate date);

    List<Crime> findByDateRange(LocalDate startDate, LocalDate endDate);

    List<Crime> findByLocation(String location);

    List<Crime> findByType(String type);

    void update(Crime crime);

    void delete(Long id);

}
