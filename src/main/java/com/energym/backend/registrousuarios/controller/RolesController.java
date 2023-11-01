package com.energym.backend.registrousuarios.controller;

import com.energym.backend.registrousuarios.model.Roles;
import com.energym.backend.registrousuarios.service.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RolesController {
    private final Logger log = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public RolesService service;

    @GetMapping("/role/id/")
    public ResponseEntity<Roles> findRoleById(@RequestParam Integer id){
        try {
            return new ResponseEntity<>(service.findRoleById(id).get(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/role/")
    public ResponseEntity<List<Roles>> getRoles(){
        return new ResponseEntity<>(service.getRoles(), HttpStatus.OK);
    }
}
