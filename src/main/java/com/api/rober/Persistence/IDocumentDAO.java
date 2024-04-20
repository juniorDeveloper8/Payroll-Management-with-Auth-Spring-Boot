package com.api.rober.Persistence;

import com.api.rober.Entity.Document;

import java.util.List;
import java.util.Optional;

public interface IDocumentDAO {

    List<Document> findAll();

    Optional<Document> findById(Integer id_doc);

    void save(Document document);

    void deleteById(Integer id_doc);
}
