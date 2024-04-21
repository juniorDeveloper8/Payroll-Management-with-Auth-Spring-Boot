package com.api.rober.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_us;

    @Column(name = "us_nombre")
    private String name;
    @Column(name = "us_apellido")
    private String lastname;
    @Column(name = "us_correo", unique = true)
    private String email;
    @Column(name = "us_contracena", unique = true, length = 255)
    private String psw;
    @Column(name = "us_telefono", unique = true)
    private String phone;
    @Column(name = "us_estado")
    private int status;

    // clave foranea

    @ManyToOne
    @JoinColumn(name= "id_area")
    @JsonIgnore
    private Area area;

    // relacion

    @OneToMany(mappedBy = "userDoc", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore

    private List<Document> documentList = new ArrayList<>();

    @OneToMany(mappedBy = "userRol", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<UsRol> usRolList = new ArrayList<>();


}
