package com.energym.backend.registrousuarios.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "role")
    private Set<Users> users;

    public Roles(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Roles() {
        this.id = 0;
        this.name = "";
    }
}
