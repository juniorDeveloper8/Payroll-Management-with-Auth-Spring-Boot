package com.api.rober.Models;

import com.api.rober.DTO.Documento.DatosRegistroDocumento;
import com.api.rober.Models.Enum.Nacionalidad;
import com.api.rober.Models.Enum.TipoDoc;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "documento")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_do", unique = true, nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoDoc tipoDoc;

    @Column(unique = true, nullable = false)
    private String numero;

    @Enumerated(EnumType.STRING)
    private Nacionalidad nacionalidad;

    //lista de empleados por numero de documento
    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<Empleado> empleadoList = new ArrayList<>();

    // metodos

    public Documento(DatosRegistroDocumento datosRegistroDocumento) {
        this.tipoDoc = datosRegistroDocumento.tipoDoc();
        this.numero = datosRegistroDocumento.numero();
        this.nacionalidad = datosRegistroDocumento.nacionalidad();
    }

}
