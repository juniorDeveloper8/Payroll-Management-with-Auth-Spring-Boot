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
@Table(name = "us_rol")
public class UsRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsRol;

    @ManyToOne
    @JoinColumn(name= "usuario_id", nullable = false)
    @JsonIgnore
    private User userRol;

    @ManyToOne
    @JoinColumn(name= "id_rol", nullable = false)
    @JsonIgnore
    private Rol rol;
}
