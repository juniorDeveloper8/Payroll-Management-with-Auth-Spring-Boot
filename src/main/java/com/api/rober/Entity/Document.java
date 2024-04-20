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

    @Column(name = "doc_cedula")
    private String icard;
    @Column(name = "doc_pasaporte")
    private String passport;
    @Column(name = "doc_ruc")
    private String ruc;

    // llave foranea
    @ManyToOne
    @JoinColumn(name= "id_docUser", nullable = false)
    @JsonIgnore
    private User userDoc;
}
