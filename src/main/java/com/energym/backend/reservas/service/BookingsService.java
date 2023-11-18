package com.energym.backend.reservas.service;

import com.energym.backend.registrousuarios.model.Users;
import com.energym.backend.reservas.model.Bookings;
import com.energym.backend.reservas.model.Classes;
import com.energym.backend.reservas.repository.BookingsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingsService {

    private final BookingsRepository repository;

    public BookingsService(BookingsRepository repository) {
        this.repository = repository;
    }

    public Bookings newBooking(Bookings booking){
        repository.newBooking(booking.getUser_id().getId(), booking.getClasses_id().getId());
        return repository.findBooking(booking.getUser_id().getId(), booking.getClasses_id().getId());
    }

    public List<Users> findUsersByClass(Long classes){
        return repository.findUsersByClass(classes);
    }

    public List<Classes> findClassesByUser(Integer user){
        return repository.findClassesByUser(user);
    }

    public List<Classes> findClassesByUserBetween(Integer user, LocalDate initialDate, LocalDate finalDate){
        return repository.findClassesByUserBetween(user, initialDate, finalDate);
    }

    public List<Classes> findClassesBetween(LocalDate initialDate, LocalDate finalDate){
        return repository.findClassesBetween(initialDate, finalDate);
    }
}
