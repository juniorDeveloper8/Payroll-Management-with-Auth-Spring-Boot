package com.api.rober.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "area")

public class AreaEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_ar;
    private String ar_nom; //nombre de la area
    private String ar_des; // descripcion del area
}
