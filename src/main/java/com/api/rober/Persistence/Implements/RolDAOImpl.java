package com.api.rober.Persistence.Implements;

import com.api.rober.Entity.Rol;
import com.api.rober.Persistence.IRolDAO;
import com.api.rober.Repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RolDAOImpl implements IRolDAO {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> findAll() {
        return (List<Rol>) rolRepository.findAll();
    }

    @Override
    public Optional<Rol> findById(Integer id_rol) {
        return rolRepository.findById(id_rol);
    }

    @Override
    public void save(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public void deleteById(Integer id_rol) {
        rolRepository.deleteById(id_rol);
    }
}
