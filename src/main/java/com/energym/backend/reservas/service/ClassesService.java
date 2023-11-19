package com.energym.backend.reservas.service;

import com.energym.backend.reservas.model.Classes;
import com.energym.backend.reservas.repository.ClassesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClassesService {

    private final ClassesRepository repository;

    public ClassesService(ClassesRepository repository) {
        this.repository = repository;
    }

    public Classes newClass(Classes classes){
        return repository.save(classes);
    }

    public Optional<Classes> findClassById(Long id){
        return repository.findById(id);
    }

    public List<Classes> findClassByDate(LocalDate date){
        return repository.findClassesByDate(date);
    }

    public List<Classes> findClassByDateAndHour(LocalDate date, LocalTime hour){
        return repository.findClassesByDateAndHour(date, hour);
    }

    public List<Classes> getClasses(){
        return (List<Classes>) repository.findAll();
    }

    public Optional<Classes> updateClass(Classes classes){
        //repository.updateClass(classes.getDate(), classes.getHour(), classes.getCode(), classes.getOwner().getId(), classes.getId());
        repository.updateClass(classes.getDate(), classes.getHour(), classes.getCode(), classes.getName(), classes.getDescription(), classes.getDuration(), classes.getImage(), classes.getPrice(), classes.getOwner().getId(), classes.getId());
        return repository.findById(classes.getId());
    }
}
