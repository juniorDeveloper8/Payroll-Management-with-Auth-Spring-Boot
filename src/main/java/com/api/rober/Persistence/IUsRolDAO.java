package com.api.rober.Persistence;

import com.api.rober.Entity.UsRol;

import java.util.List;
import java.util.Optional;

public interface IUsRolDAO {

    List<UsRol> findAll();

    Optional<UsRol> findById(Integer idUsRol);

    void save(UsRol usRol);

    void deleteById(Integer idUsRol);
}
