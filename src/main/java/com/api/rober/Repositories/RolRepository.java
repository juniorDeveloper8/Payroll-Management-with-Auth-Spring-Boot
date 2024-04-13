package com.api.rober.Repositories;

import com.api.rober.Entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {

    List<RolEntity> findByDate(Date fecha);

    RolEntity findById(int id_rol);
}
