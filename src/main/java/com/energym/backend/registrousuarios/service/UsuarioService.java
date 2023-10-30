package com.energym.backend.registrousuarios.service;

import com.energym.backend.registrousuarios.controller.UsuarioController;
import com.energym.backend.registrousuarios.model.Usuario;
import com.energym.backend.registrousuarios.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Service
public class UsuarioService {

    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private static String ENCRYPT_KEY = "energym-energym-";

    private final UsuarioRepository repositorio;

    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public String encript(String password) throws Exception{
        Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /*public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repositorio.findById(Integer.parseInt(username));
        if (usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario o contraseña incorrectos");
        }
        return new User(usuario.get().getNombre(), usuario.get().getPassword(), mapearAutoridadesRoles(usuario.get().getRol()));
    }//*/

    public Usuario guardar(Usuario usuario){
        try{
            usuario.setPassword(encript(usuario.getPassword()));
            log.info(usuario.getPassword());
        }catch (Exception ex){
            log.error(ex.getMessage());
            return null;
        }
        return repositorio.save(usuario);
    }

    /*private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Rol rol){
        return Collections.singleton(new SimpleGrantedAuthority(rol.getNombre()));
    }//*/
}
