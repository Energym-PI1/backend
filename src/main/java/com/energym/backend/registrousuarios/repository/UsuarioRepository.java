package com.energym.backend.registrousuarios.repository;

import com.energym.backend.registrousuarios.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}
