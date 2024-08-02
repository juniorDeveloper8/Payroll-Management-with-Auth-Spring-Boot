package com.api.rober.Models;

import com.api.rober.DTO.Area.DatosRegistroArea;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "area")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area", unique = true, nullable = false)
    private Integer id;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nom;
    @Column(name = "descripcion", unique = true, nullable = false)
    private String descrip;

    //lista de empleados por area
    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<Empleado> empleadoList = new ArrayList<>();


    // metodo save
    public Area(DatosRegistroArea datosRegistroArea) {
        this.nom = datosRegistroArea.nom();
        this.descrip = datosRegistroArea.descrip();
    }
}
