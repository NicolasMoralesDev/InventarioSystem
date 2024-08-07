package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.entity.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    List findAll();
    Optional findById(Long id);
    Permission save(Permission permission);
    void deleteById(Long id);
    Permission update(Permission permission);
}
