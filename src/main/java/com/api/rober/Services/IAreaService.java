package com.api.rober.Services;

import com.api.rober.Entity.Area;

import java.util.List;
import java.util.Optional;

public interface IAreaService {
    List<Area> findAll();

    Optional<Area> findById(Integer id_ar);

    void save(Area area);

    void deleteById(Integer id_ar);
}
