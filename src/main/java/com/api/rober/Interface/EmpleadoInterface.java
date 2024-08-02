package com.api.rober.Interface;

import com.api.rober.Models.Empleado;
import com.api.rober.Models.FichaPago;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoInterface extends CrudRepository<Empleado, Integer> {

    List<Empleado> findByActivoTrue();

    List<FichaPago> findByNom(String nom); // buscar rol por nombre
}
