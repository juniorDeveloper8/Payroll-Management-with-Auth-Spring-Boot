package com.api.rober.Repositories;

import com.api.rober.Entity.ContactoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<ContactoEntity, Integer> {

    List<ContactoEntity> findByTelf(String con_telefono);
}
