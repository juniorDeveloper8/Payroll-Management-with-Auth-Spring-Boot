package com.api.rober.Services.Implements;

import com.api.rober.Entity.Rol;
import com.api.rober.Persistence.IRolDAO;
import com.api.rober.Services.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolDAO rolDAO;

    @Override
    public List<Rol> findAll() {
        return rolDAO.findAll();
    }

    @Override
    public Optional<Rol> findById(Integer id_rol) {
        return rolDAO.findById(id_rol);
    }

    @Override
    public void save(Rol rol) {
        rolDAO.save(rol);
    }

    @Override
    public void deleteById(Integer id_rol) {
        rolDAO.deleteById(id_rol);
    }
}
