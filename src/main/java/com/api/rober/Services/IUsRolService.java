package com.api.rober.Services;

import com.api.rober.Entity.UsRol;

import java.util.List;
import java.util.Optional;

public interface IUsRolService {
    List<UsRol> findAll();

    Optional<UsRol> findById(Integer idUsRol);

    void save(UsRol usRol);

    void deleteById(Integer idUsRol);
}
