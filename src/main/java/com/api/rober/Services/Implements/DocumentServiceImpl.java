package com.api.rober.Services.Implements;

import com.api.rober.Entity.Document;
import com.api.rober.Persistence.IDocumentDAO;
import com.api.rober.Services.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements IDocumentService {

    @Autowired
    private IDocumentDAO documentDAO;

    @Override
    public List<Document> findAll() {
        return documentDAO.findAll();
    }

    @Override
    public Optional<Document> findById(Integer id_doc) {
        return documentDAO.findById(id_doc);
    }

    @Override
    public void save(Document document) {
        documentDAO.save(document);
    }

    @Override
    public void deleteById(Integer id_doc) {
        documentDAO.deleteById(id_doc);
    }
}
