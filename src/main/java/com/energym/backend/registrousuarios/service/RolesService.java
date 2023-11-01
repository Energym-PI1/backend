package com.energym.backend.registrousuarios.service;

import com.energym.backend.registrousuarios.controller.UsersController;
import com.energym.backend.registrousuarios.model.Roles;
import com.energym.backend.registrousuarios.repository.RolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {
    private final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final RolesRepository repository;

    public RolesService(RolesRepository repository) {
        this.repository = repository;
    }

    public Optional<Roles> findRoleById(Integer id){
        return repository.findById(id);
    }

    public List<Roles> getRoles(){
        return (List<Roles>) repository.findAll();
    }
}
