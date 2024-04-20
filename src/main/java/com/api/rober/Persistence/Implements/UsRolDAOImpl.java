package com.api.rober.Persistence.Implements;

import com.api.rober.Entity.UsRol;
import com.api.rober.Persistence.IUsRolDAO;
import com.api.rober.Repositories.UserRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsRolDAOImpl implements IUsRolDAO {

    @Autowired
    private UserRolRepository userRolRepository;

    @Override
    public List<UsRol> findAll() {
        return (List<UsRol>) userRolRepository.findAll();
    }

    @Override
    public Optional<UsRol> findById(Integer idUsRol) {
        return userRolRepository.findById(idUsRol);
    }

    @Override
    public void save(UsRol usRol) {
        userRolRepository.save(usRol);
    }

    @Override
    public void deleteById(Integer idUsRol) {
        userRolRepository.deleteById(idUsRol);
    }
}
