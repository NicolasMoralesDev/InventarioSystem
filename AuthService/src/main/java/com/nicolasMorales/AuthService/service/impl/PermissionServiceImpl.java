package com.nicolasMorales.AuthService.service.impl;

import com.nicolasMorales.AuthService.model.Permission;
import com.nicolasMorales.AuthService.repository.IPermissionRepository;
import com.nicolasMorales.AuthService.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public List findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission update(Permission permission) {
        return permissionRepository.save(permission);
    }
}
