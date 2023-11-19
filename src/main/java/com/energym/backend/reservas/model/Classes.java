package com.energym.backend.reservas.model;

import com.energym.backend.registrousuarios.model.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "classes")
@NoArgsConstructor
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    private LocalDate date;

    @Column(name = "hour")
    @Temporal(TemporalType.TIME)
    @Getter
    @Setter
    private LocalTime hour;

    @Column(name = "code")
    @Getter
    @Setter
    private String code;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "price")
    @Getter
    @Setter
    private Double price;

    @Column(name = "duration")
    @Getter
    @Setter
    private Integer duration;

    @Column(name = "image")
    @Getter
    @Setter
    private String image;

    @JoinColumn(name = "owner")
    @ManyToOne
    @Getter
    @Setter
    private Professionals owner;

    @ManyToMany(mappedBy = "bookings")
    private Set<Users> users;

    public Classes(Long id) {
        this.id = id;
    }

    public Classes(Long id, LocalDate date, LocalTime hour, String code, String name, String description, Double price, Integer duration, String image, Professionals owner) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.image = image;
        this.owner = owner;
    }
}
