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

    public Classes(Long id, LocalDate date, LocalTime hour, Professionals owner) {
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.owner = owner;
    }
}
