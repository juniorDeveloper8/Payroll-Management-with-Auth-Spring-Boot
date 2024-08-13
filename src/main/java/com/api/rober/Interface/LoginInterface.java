package com.api.rober.Interface;

import com.api.rober.Models.Account.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginInterface extends CrudRepository<Login, Integer> {
    UserDetails findByUsername(String username);

    boolean existsByUsername(String username);
}
