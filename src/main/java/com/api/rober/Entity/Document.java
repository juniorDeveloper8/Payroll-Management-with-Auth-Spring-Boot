package com.api.rober.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documento")

public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_doc;

    @Column(name = "tipo_documento")
    private Documento documento;

    @Column(name = "numero_documento")
    private String NumeroDocumento;


    // llave foranea
    @ManyToOne
    @JoinColumn(name= "id_docUser", nullable = false)
    @JsonIgnore
    private User userDoc;
}
