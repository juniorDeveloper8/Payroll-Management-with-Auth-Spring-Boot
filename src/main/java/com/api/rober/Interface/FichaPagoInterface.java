package com.api.rober.Interface;

import com.api.rober.Models.FichaPago;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaPagoInterface extends CrudRepository<FichaPago, Integer> {
}
