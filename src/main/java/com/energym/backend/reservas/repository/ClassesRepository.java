package com.energym.backend.reservas.repository;

import com.energym.backend.reservas.model.Classes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ClassesRepository  extends CrudRepository<Classes, Long> {

    List<Classes> findClassesByDate(LocalDate date);

    List<Classes> findClassesByDateAndHour(LocalDate date, LocalTime hour);

    @Modifying
    @Transactional
    @Query("update Classes u set u.date=:date, u.hour=:hour, u.code=:code, u.name=:name, u.description=:description, u.duration=:duration, u.image=:image, u.price=:price, u.owner.id=:professional where u.id=:id")
    void updateClass(@Param("date") LocalDate date, @Param("hour") LocalTime hour, @Param("code") String code, @Param("name") String name, @Param("description") String description, @Param("duration") Integer duration, @Param("image") String image, @Param("price") Double price, @Param("professional") Integer professional, @Param("id") Long id);
}
