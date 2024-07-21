package com.nicolasMorales.InventariumSystem.services.impl;

import com.nicolasMorales.InventariumSystem.entity.Permission;
import com.nicolasMorales.InventariumSystem.repository.IPermissionRepository;
import com.nicolasMorales.InventariumSystem.services.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    /**
     * @see IPermissionService#findAll()
     */
    @Override
    public List findAll() {
        return permissionRepository.findAll();
    }

    /**
     * @see IPermissionService#findById(Long)
     */
    @Override
    public Optional findById(Long id) {
        return permissionRepository.findById(id);
    }

    /**
     * @see IPermissionService#save(Permission)
     */
    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    /**
     * @see IPermissionService#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }

    /**
     * @see IPermissionService#update(Permission)
     */
    @Override
    public Permission update(Permission permission) {
        return permissionRepository.save(permission);
    }
}