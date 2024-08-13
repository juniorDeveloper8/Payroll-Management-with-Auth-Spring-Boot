package com.api.rober.Interface;

import com.api.rober.Models.FichaPago;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichaPagoInterface extends CrudRepository<FichaPago, Integer> {
    List<FichaPago> findByEmpleadoNom(String nom);

    /*
    @Query("SELECT f FROM FichaPago f WHERE f.empleado.nom = :nombre")
    List<FichaPago> findByEmpleadoNombre(String nombre);
     */
}
