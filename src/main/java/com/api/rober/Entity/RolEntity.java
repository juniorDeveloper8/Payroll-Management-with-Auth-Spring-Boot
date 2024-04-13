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
@Table(name = "rol")

public class RolEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_rol;
    private String codigo;
    private Date fecha;

    private float rol_ingresos;
    private float rol_egresos;
    private float rol_sueldo;
    /*
    * funcion para logica de negoscio osea calculo del sueldo
    * funcionpara el autto generador del codigo
    *  */
}
