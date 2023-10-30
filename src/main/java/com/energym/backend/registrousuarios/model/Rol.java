package com.energym.backend.registrousuarios.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "nombre", nullable = false)
    @Getter
    @Setter
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rol")
    private Set<Usuario> usuarios;

    public Rol(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Rol() {
        this.id = 0;
        this.nombre = "";
    }
}
