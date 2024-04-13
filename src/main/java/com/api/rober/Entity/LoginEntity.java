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
@Table(name = "login") // esta tabla es solo para un mayor control de los datos
public class LoginEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_lo;

    private boolean intentos;
    /*
    * funciono de incremento de intentos q tenga
    * bloqueo del usuario
    * usuario activo e inactivo
    * */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UserEntity userEntity;
}
