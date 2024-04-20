package com.api.rober.Persistence;

import com.api.rober.Entity.Area;

import java.util.List;
import java.util.Optional;

public interface IAreaDAO {

    List<Area> findAll();

    Optional<Area> findById(Integer id_ar);

    void save(Area area);

    void deleteById(Integer id_ar);
}
