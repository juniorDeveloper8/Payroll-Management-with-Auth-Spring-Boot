package com.api.rober.Repositories;

import com.api.rober.Entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, Integer> {

    List<AreaEntity> findByNombre(String ar_nombre);

    AreaEntity findById(int id);
}
