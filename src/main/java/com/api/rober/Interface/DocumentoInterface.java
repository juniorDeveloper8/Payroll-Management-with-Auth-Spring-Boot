package com.api.rober.Interface;

import com.api.rober.Models.Documento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoInterface extends CrudRepository<Documento, Integer> {

    List<Documento> findByNumero(String numero);
}
