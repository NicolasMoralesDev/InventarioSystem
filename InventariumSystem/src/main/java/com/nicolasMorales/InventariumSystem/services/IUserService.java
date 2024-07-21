package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.entity.UserSec;

import java.util.List;
import java.util.Optional;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Usuarios.
 */
public interface IUserService {

     List findAll();
     Optional findById(Long id);
     UserSec save(UserSec userSec);
     void deleteById(Long id);
     void update(UserSec userSec);
     String encriptPasword(String password);
}