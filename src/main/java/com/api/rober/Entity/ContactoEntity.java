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
@Table(name = "contacto")

public class ContactoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id_con;
    private String con_telefono;

    // relación

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "us_id_con")
    private UserEntity userEntity;
}
