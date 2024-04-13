package com.api.rober.Repositories;

import com.api.rober.Entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {

    List<DocumentEntity> findByDocument(String document);

    DocumentEntity findById(int id_doc);

}
