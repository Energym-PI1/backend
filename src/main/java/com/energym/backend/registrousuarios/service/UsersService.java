package com.energym.backend.registrousuarios.service;

import com.energym.backend.registrousuarios.controller.UsersController;
import com.energym.backend.registrousuarios.model.Users;
import com.energym.backend.registrousuarios.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Service
public class UsersService {

    private final Logger log = LoggerFactory.getLogger(UsersController.class);

    private static String ENCRYPT_KEY = "energym-energym-";

    private final UsersRepository repository;

    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    public String encript(String password) throws Exception{
        Key aesKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public Users newUser(Users user){
        try{
            user.setPassword(encript(user.getPassword()));
        }catch (Exception ex){
            log.error(ex.getMessage());
            return null;
        }
        return repository.save(user);
    }

    public Users findUserbyEmail(String email) throws Exception{
        Users found = repository.findUserByEmail(email);
        if(found == null){
            throw new Exception("Usuario no encontrado");
        }
        return found;
    }

    public Optional<Users> findUserById(Integer id){
        return repository.findById(id);
    }

    public List<Users> getUsers(){
        return (List<Users>) repository.findAll();
    }

    public Users login(String email, String password) throws Exception {
        password = encript(password);
        Users user = repository.findUserByEmailAndPassword(email, password);
        if (user == null){
            throw new Exception("Usuario no encontrado");
        }
        return user;
    }

    public Users updateUser(Users u){
        repository.updateUser(u.getId(), u.getName(), u.getEmail(), u.getTelephone(), u.getRole());
        return repository.findById(u.getId()).get();
    }

    public Users updatePassword(Integer id, String password){
        try{
            password = encript(password);
        }catch (Exception ex){
            log.error(ex.getMessage());
            return null;
        }
        repository.updatePassword(id, password);
        return repository.findById(id).get();
    }
}
