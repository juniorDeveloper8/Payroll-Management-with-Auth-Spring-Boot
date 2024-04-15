package com.api.rober.Repositories;

import com.api.rober.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Page<UserEntity> findByEstadoTrue(Pageable paginacion);
    UserEntity findById(int id_us);
}
