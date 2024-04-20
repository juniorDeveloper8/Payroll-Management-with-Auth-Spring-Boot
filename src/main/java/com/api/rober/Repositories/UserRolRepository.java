package com.api.rober.Repositories;

import com.api.rober.Entity.UsRol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolRepository extends CrudRepository<UsRol, Integer> {
}
