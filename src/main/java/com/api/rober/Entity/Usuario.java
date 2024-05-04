package com.api.rober.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuario")
@Entity(name = "Usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String login;
    private String clave;
    private boolean activo;
}

