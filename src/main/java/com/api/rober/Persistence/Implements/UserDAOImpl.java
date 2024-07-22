package com.api.rober.Persistence.Implements;

import com.api.rober.Entity.User;
import com.api.rober.Persistence.IUserDAO;
import com.api.rober.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImpl implements IUserDAO {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findByStatusTrue() {
        return (List<User>) userRepository.findByStatusTrue();
    }

    @Override
    public Optional<User> findById(Integer id_us) {
        return userRepository.findById(id_us);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id_us) {
        userRepository.deleteById(id_us);
    }
}
