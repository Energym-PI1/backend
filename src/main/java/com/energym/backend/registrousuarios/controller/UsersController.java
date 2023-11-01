package com.energym.backend.registrousuarios.controller;

import com.energym.backend.registrousuarios.model.Users;
import com.energym.backend.registrousuarios.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    private final Logger log = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public UsersService service;

    @PostMapping(value = "/user")
    public ResponseEntity<Users> newUser(@RequestBody Users user){
        try {
            Users created = service.newUser(user);
            log.info("Usuario creado correctamente: Nombre - " + created.getName());
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("No se pudo crear el usuario: Nombre - " + user.getName());
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/user/email/")
    public ResponseEntity<Users> findUserByEmail(@RequestParam String email){
        try{
            Users found = service.findUserbyEmail(email);
            log.info("Usuario encontrado: Nombre - " + found.getName());
            return ResponseEntity.ok(found);
        }catch (Exception ex){
            log.error("Usuario no encontrado");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/id/")
    public ResponseEntity<Users> findUserById(@RequestParam Integer id){
        Optional<Users> found = service.findUserById(id);
        if (found.isPresent()){
            log.info("Usuario encontrado: Nombre - " + found.get().getName());
            return ResponseEntity.ok(found.get());
        }
        else {
            log.error("Usuario no encontrado");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/")
    public ResponseEntity<List<Users>> getUsers(){
        return ResponseEntity.ok(service.getUsers());
    }

    @PutMapping(value = "/user/")
    public ResponseEntity<Users> updateUser(@RequestBody Users user){
        try{
            Users updated = service.updateUser(user);
            log.info("Rest request actualizar un usuario: " + updated.getId() + " - " + updated.getName());
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }catch(Exception ex){
            log.info("Rest bad request actualizar un usuario " + user.getId());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "/user/update_password/")
    public ResponseEntity<Users> updatePassword(@RequestBody Users user){
        try{
            Users updated = service.updatePassword(user.getId(), user.getPassword());
            log.info("Rest request actualizar un usuario: " + updated.getId() + " - " + updated.getName());
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }catch(Exception ex){
            log.info("Rest bad request actualizar un usuario " + user.getId());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}
