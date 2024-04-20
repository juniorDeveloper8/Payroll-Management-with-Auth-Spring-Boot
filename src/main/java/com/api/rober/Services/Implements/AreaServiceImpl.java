package com.api.rober.Services.Implements;

import com.api.rober.Entity.Area;
import com.api.rober.Persistence.IAreaDAO;
import com.api.rober.Services.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private IAreaDAO areaDAO;

    @Override
    public List<Area> findAll() {
        return areaDAO.findAll();
    }

    @Override
    public Optional<Area> findById(Integer id_ar) {
        return areaDAO.findById(id_ar);
    }

    @Override
    public void save(Area area) {
        areaDAO.save(area);
    }

    @Override
    public void deleteById(Integer id_ar) {
        areaDAO.deleteById(id_ar);
    }
}
