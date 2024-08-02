package com.api.rober.Interface;

import com.api.rober.Models.Area;
import com.api.rober.Models.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaInterface extends CrudRepository<Area, Integer> {

    List<Empleado> findByNom(String nom); //retornamos empleados q pertenescan a cierta area
}
