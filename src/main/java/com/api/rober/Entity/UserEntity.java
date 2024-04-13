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
@Table(name = "usuario")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_us;
    private  String us_nombre;
    private  String us_apellido;
    private  String us_correo;
    private  String us_contracena;

    private boolean estado;

    // relación

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area")
    private AreaEntity areaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipoDoc")
    private DocumentEntity documentEntity;
}

