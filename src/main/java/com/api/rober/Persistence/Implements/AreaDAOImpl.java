package com.api.rober.Persistence.Implements;

import com.api.rober.Entity.Area;
import com.api.rober.Persistence.IAreaDAO;
import com.api.rober.Repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AreaDAOImpl implements IAreaDAO {

    @Autowired
    private AreaRepository repository;

    @Override
    public List<Area> findAll() {
        return (List<Area>) repository.findAll();
    }

    @Override
    public Optional<Area> findById(Integer id_ar) {
        return repository.findById(id_ar);
    }

    @Override
    public void save(Area area) {
        repository.save(area);
    }

    @Override
    public void deleteById(Integer id_ar) {
        repository.deleteById(id_ar);
    }
}
