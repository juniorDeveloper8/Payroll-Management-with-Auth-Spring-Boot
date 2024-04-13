package com.api.rober.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documento")

public class DocumentEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_doc;
    private  String doc_cedula;
    private  String doc_pasaporte;
    private  String doc_ruc;

}
