package com.api.rober.Services.Implements;

import com.api.rober.Entity.User;
import com.api.rober.Persistence.IUserDAO;
import com.api.rober.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<User> findByStatusTrue() {
        return userDAO.findByStatusTrue();
    }

    @Override
    public Optional<User> findById(Integer id_us) {
        return userDAO.findById(id_us);
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void deleteById(Integer id_us) {
        userDAO.deleteById(id_us);
    }
}
