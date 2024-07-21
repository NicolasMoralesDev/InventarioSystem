package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.entity.Permission;

import java.util.List;
import java.util.Optional;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Permisos.
 */
public interface IPermissionService {

    List findAll();
    Optional findById(Long id);
    Permission save(Permission permission);
    void deleteById(Long id);
    Permission update(Permission permission);
}
