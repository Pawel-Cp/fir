package org.example.dao;

import org.example.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User add(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    List<User> findAll();

    void delete(Long id);

    boolean update(User user);

}
