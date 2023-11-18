package com.energym.backend.reservas.controller;

import com.energym.backend.reservas.model.Classes;
import com.energym.backend.reservas.service.ClassesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
public class ClassesController {

    private final Logger log = LoggerFactory.getLogger(ClassesController.class);

    @Autowired
    public ClassesService service;

    @Operation(summary = "Agrega un nuevo registro en la tabla classes. La fecha tiene formato yyyy-MM-dd y la hora HH:mm:ss")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La clase creada"),
    })
    @PostMapping(value = "/classes")
    public ResponseEntity<Classes> newClass(@RequestBody Classes classes){
        Classes created = service.newClass(classes);
        log.info("Clase creada correctamente: Fecha - " + created.getDate().toString());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca en la tabla classes la clase que coincida con el id pasado como parámetro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "La clase que coincida con el criterio de búsqueda"),
            @ApiResponse(responseCode = "404", description = "Null cuando la clase no se encuentra")
    })
    @GetMapping(value = "/classes/id/")
    public ResponseEntity<Classes> findClassById(@RequestParam Long id){
        Optional<Classes> found = service.findClassById(id);
        if (found.isPresent()) {
            log.info("Clase encontrada: Fecha - " + found.get().getDate());
            return new ResponseEntity<>(found.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Busca en la tabla classes todas las clases que coincida con la fecha pasada como parámetro. La fecha tiene formato yyyy-MM-dd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Las clases que coincidan con el criterio de búsqueda")
    })
    @GetMapping(value = "/classes/date/")
    public ResponseEntity<List<Classes>>findClassByDate(@RequestParam LocalDate date){
        return new ResponseEntity<>(service.findClassByDate(date), HttpStatus.OK);
    }

    @Operation(summary = "Busca en la tabla classes todas las clases que coincida con la fecha y hora pasados como parámetro. La fecha tiene formato yyyy-MM-dd y la hora HH:mm:ss")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Las clases que coincidan con los criterios de búsqueda")
    })
    @GetMapping(value = "/classes/date_hour/")
    public ResponseEntity<List<Classes>> findClassByDateAndHour(@RequestParam LocalDate date, @RequestParam LocalTime hour){
        return new ResponseEntity<>(service.findClassByDateAndHour(date, hour), HttpStatus.OK);
    }

    @Operation(summary = "Busca todas las clases en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas las clases en la base de datos")
    })
    @GetMapping(value = "/classes")
    public ResponseEntity<List<Classes>> getClasses(){
        return new ResponseEntity<>(service.getClasses(), HttpStatus.OK);
    }

    @Operation(summary = "Actualiza la información de una clase. La fecha tiene formato yyyy-MM-dd y la hora HH:mm:ss")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La clase actualizada"),
            @ApiResponse(responseCode = "409", description = "Null cuando no se actualiza la clase"),
    })
    @PutMapping(value = "/classes")
    public ResponseEntity<Classes> updateClass(@RequestBody Classes classes){
        try{
            Classes updated = service.updateClass(classes);
            log.info("Rest request actualizar una clase: " + updated.getId());
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }catch(Exception ex){
            log.info("Rest bad request actualizar una clase " + classes.getId());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}
