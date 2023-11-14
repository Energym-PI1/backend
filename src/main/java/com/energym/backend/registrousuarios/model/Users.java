package com.energym.backend.registrousuarios.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class Users {

    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;


    @Column(name = "email", unique = true)
    @Getter
    @Setter
    private String email;


    @Column(name = "password")
    @Getter
    @Setter
    private String password;


    @Column(name = "telephone")
    @Getter
    @Setter
    private String telephone;


    @JoinColumn(name = "role")
    @ManyToOne
    @Getter
    @Setter
    private Roles role;

    public Users(String email, String password){
        this.email = email;
        this.password = password;
    }
}
