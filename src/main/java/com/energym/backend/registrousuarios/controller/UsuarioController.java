package com.energym.backend.registrousuarios.controller;

import com.energym.backend.registrousuarios.model.Usuario;
import com.energym.backend.registrousuarios.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    public UsuarioService usuarioService;

    @PostMapping(value = "/")
    public ResponseEntity<Usuario> nuevo(@RequestBody Usuario usuario){
        log.info("Recibido el post");
        try {
            log.info("Creando el usuario");
            Usuario creado = usuarioService.guardar(usuario);
            log.info("Usuario creado correctamente: Nombre - " + creado.getNombre());
            return new ResponseEntity<>(creado, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("No se pudo crear el usuario: Nombre - " + usuario.getNombre());
            return null;
        }
    }

    @GetMapping(value = "/inicio")
    public ResponseEntity<?> inicio(){
        return ResponseEntity.ok("Hola Mundo");
    }
}
