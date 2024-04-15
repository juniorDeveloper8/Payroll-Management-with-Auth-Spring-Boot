package com.api.rober.Repositories;

import com.api.rober.Entity.ContactoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<ContactoEntity, Integer> {

    public abstract ArrayList<ContactoEntity> findById(int id_con);

}
