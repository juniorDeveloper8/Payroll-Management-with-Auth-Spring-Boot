package com.api.rober.Services;

import com.api.rober.Entity.Document;

import java.util.List;
import java.util.Optional;

public interface IDocumentService {
    List<Document> findAll();

    Optional<Document> findById(Integer id_doc);

    void save(Document document);

    void deleteById(Integer id_doc);
}
