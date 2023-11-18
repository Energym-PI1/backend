package com.energym.backend.reservas.repository;

import com.energym.backend.registrousuarios.model.Users;
import com.energym.backend.reservas.model.Bookings;
import com.energym.backend.reservas.model.BookingsPK;
import com.energym.backend.reservas.model.Classes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface BookingsRepository extends CrudRepository<Bookings, BookingsPK> {

    @Modifying
    @Transactional
    @Query(value = "insert into bookings(user_id, classes_id) VALUES (:userId, :classId)", nativeQuery = true)
    void newBooking(@Param("userId") Integer userId, @Param("classId") Long classId);

    @Query("select b from Bookings b where b.classes_id.id = :classId and b.user_id.id = :userId")
    Bookings findBooking(Integer userId, Long classId);

    @Query("select u from Bookings b join Users u on b.user_id.id = u.id where b.classes_id.id = :classId")
    List<Users> findUsersByClass(Long classId);

    @Query("select c from Bookings b join Classes c on b.classes_id.id = c.id where b.user_id.id = :user")
    List<Classes> findClassesByUser(Integer user);

    @Query("select c from Bookings b join Classes c on b.classes_id.id = c.id where b.user_id.id = :user and c.date between :initialDate and :finalDate")
    List<Classes> findClassesByUserBetween(Integer user, LocalDate initialDate, LocalDate finalDate);

    @Query("select c from Bookings b join Classes c on b.classes_id.id = c.id where c.date between :initialDate and :finalDate")
    List<Classes> findClassesBetween(LocalDate initialDate, LocalDate finalDate);
}
