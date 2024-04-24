package com.api.rober.Controllers;

import com.api.rober.Controllers.DTO.DocumentDTO;
import com.api.rober.Entity.Document;
import com.api.rober.Services.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")

public class DocumentController {

    @Autowired
    private IDocumentService documentService;

    @GetMapping("/allDocuments")
    public ResponseEntity<?> findAll() {
        List<DocumentDTO> documentDTOList = documentService.findAll()
                .stream()
                .map(document -> DocumentDTO.builder()
                        .id_doc(document.getId_doc())
                        .ruc(document.getRuc())
                        .passport(document.getPassport())
                        .icard(document.getIcard())
                        .userDoc(document.getUserDoc())
                        .build()
                )
                .toList();

        return ResponseEntity.ok(documentDTOList);
    }

    @PostMapping("/document")
    public ResponseEntity<?> save(@RequestBody DocumentDTO documentDTO) throws URISyntaxException {
        if (documentDTO.getPassport().isBlank() ||
                documentDTO.getIcard().isBlank() ||
                documentDTO.getRuc().isBlank() ||
                documentDTO.getUserDoc() == null
        ) {
            return ResponseEntity.badRequest().build();
        }
        Document document = Document.builder()
                .ruc(documentDTO.getRuc())
                .icard(documentDTO.getIcard())
                .passport(documentDTO.getPassport())
                .userDoc(documentDTO.getUserDoc())
                .build();
        documentService.save(document);
        return ResponseEntity.created(new URI("/api/document")).build();
    }

    @DeleteMapping("/document/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {

        if (id != null) {
            documentService.deleteById(id);
            return ResponseEntity.ok("Documento eliminado ");
        }
        return ResponseEntity.badRequest().build();
    }
}
