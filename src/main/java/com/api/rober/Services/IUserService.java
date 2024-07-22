package com.api.rober.Services;

import com.api.rober.Entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findByStatusTrue();

    Optional<User> findById(Integer id_us);

    void save(User user);

    void deleteById(Integer id_us);
}
