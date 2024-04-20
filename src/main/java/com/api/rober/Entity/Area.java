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
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ar;

    @Column(name = "ar_nom")
    private String name;

    @Column(name = "ar_des")
    private String des;

    //relacion

    @OneToMany(mappedBy = "areaUs", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<User> userList = new ArrayList<>();
}
