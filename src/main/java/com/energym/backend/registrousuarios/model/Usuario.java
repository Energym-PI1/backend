package com.energym.backend.registrousuarios.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "nombre")
    @Getter
    @Setter
    private String nombre;


    @Column(name = "email")
    @Getter
    @Setter
    private String email;


    @Column(name = "password")
    @Getter
    @Setter
    private String password;


    @Column(name = "telefono")
    @Getter
    @Setter
    private String telefono;


    @JoinColumn(name = "rol")
    @ManyToOne
    @Getter
    @Setter
    private Rol rol;
}
