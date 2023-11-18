package com.energym.backend.reservas.controller;

import com.energym.backend.registrousuarios.model.Users;
import com.energym.backend.reservas.model.Bookings;
import com.energym.backend.reservas.model.Classes;
import com.energym.backend.reservas.service.BookingsService;
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
import java.util.List;

@RestController
public class BookingsController {

    private final Logger log = LoggerFactory.getLogger(BookingsController.class);

    @Autowired
    public BookingsService service;

    @Operation(summary = "Agrega un nuevo registro en la tabla bookings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "La reserva creada"),
    })
    @PostMapping(value = "/bookings")
    public ResponseEntity<Bookings> newBooking(@RequestBody Bookings booking){
        log.info(booking.getUser_id().getId().toString());
        log.info(booking.getClasses_id().getId().toString());
        Bookings created = service.newBooking(booking);
        log.info("Reserva creada correctamente: Id - " + created.getClasses_id().getId());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca los usuarios participantes de una determinada clase buscando por el id de la clase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Los usuarios participantes de la clase")
    })
    @GetMapping(value = "/bookings/class/")
    public ResponseEntity<List<Users>> findUsersByClass(@RequestParam Long classes){
        return new ResponseEntity<>(service.findUsersByClass(classes), HttpStatus.OK);
    }

    @Operation(summary = "Busca las clases a las que un usuario está apuntado buscando por el id del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Las clases a las que el usuario está apuntado")
    })
    @GetMapping(value = "/bookings/user/")
    public ResponseEntity<List<Classes>> findClassesByUser(@RequestParam Integer user){
        return new ResponseEntity<>(service.findClassesByUser(user), HttpStatus.OK);
    }

    @Operation(summary = "Busca las clases a las que un usuario está apuntado buscando por el id del usuario y un rango de fechas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Las clases a las que el usuario está apuntado")
    })
    @GetMapping(value = "/bookings/user/between/")
    public ResponseEntity<List<Classes>> findClassesByUserBetween(@RequestParam Integer user, @RequestParam LocalDate initialDate, @RequestParam LocalDate finalDate){
        return new ResponseEntity<>(service.findClassesByUserBetween(user, initialDate, finalDate), HttpStatus.OK);
    }

    @Operation(summary = "Busca las clases que se impartirán en un rango de fechas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Las clases que se impartirán en el rango de fechas")
    })
    @GetMapping(value = "/bookings/between/")
    public ResponseEntity<List<Classes>> findClassesBetween(@RequestParam LocalDate initialDate, @RequestParam LocalDate finalDate){
        return new ResponseEntity<>(service.findClassesBetween(initialDate, finalDate), HttpStatus.OK);
    }
}
