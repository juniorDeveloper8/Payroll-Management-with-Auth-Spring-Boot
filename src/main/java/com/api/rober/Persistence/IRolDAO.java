package com.api.rober.Persistence;

import com.api.rober.Entity.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolDAO {

    List<Rol> findAll();

    Optional<Rol> findById(Integer id_rol);

    void save(Rol rol);

    void deleteById(Integer id_rol);
}
