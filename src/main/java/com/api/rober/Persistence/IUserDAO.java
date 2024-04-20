package com.api.rober.Persistence;

import com.api.rober.Entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    List<User> findAll();

    Optional<User> findById(Integer id_us);

    void save(User user);

    void deleteById(Integer id_us);
}
