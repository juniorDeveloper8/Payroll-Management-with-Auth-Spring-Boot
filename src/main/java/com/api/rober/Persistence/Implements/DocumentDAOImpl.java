package com.api.rober.Persistence.Implements;

import com.api.rober.Entity.Document;
import com.api.rober.Persistence.IDocumentDAO;
import com.api.rober.Repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DocumentDAOImpl implements IDocumentDAO {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public List<Document> findAll() {
        return (List<Document>) documentRepository.findAll();
    }

    @Override
    public Optional<Document> findById(Integer id_doc) {
        return documentRepository.findById(id_doc);
    }

    @Override
    public void save(Document document) {
        documentRepository.save(document);
    }

    @Override
    public void deleteById(Integer id_doc) {
        documentRepository.deleteById(id_doc);
    }
}
