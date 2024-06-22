package com.nicolasMorales.InventariumSystem.services.impl;

import com.nicolasMorales.InventariumSystem.entity.Role;
import com.nicolasMorales.InventariumSystem.repository.IRoleRepository;
import com.nicolasMorales.InventariumSystem.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }
}
