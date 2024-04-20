package com.api.rober.Services.Implements;

import com.api.rober.Entity.UsRol;
import com.api.rober.Persistence.IUsRolDAO;
import com.api.rober.Services.IUsRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsRolServiceImpl implements IUsRolService {

    @Autowired
    private IUsRolDAO usRolDAO;

    @Override
    public List<UsRol> findAll() {
        return usRolDAO.findAll();
    }

    @Override
    public Optional<UsRol> findById(Integer idUsRol) {
        return usRolDAO.findById(idUsRol);
    }

    @Override
    public void save(UsRol usRol) {
        usRolDAO.save(usRol);
    }

    @Override
    public void deleteById(Integer idUsRol) {
        usRolDAO.deleteById(idUsRol);
    }
}
