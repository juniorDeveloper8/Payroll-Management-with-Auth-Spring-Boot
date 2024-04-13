package com.api.rober.Repositories;

import com.api.rober.Entity.UserRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolRepository extends JpaRepository<UserRolEntity, Integer> {

    UserRolEntity findById(int idUsRol);
}
