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

    /**
     * @see IRoleService#findAll()
     */
    @Override
    public List findAll() {
        return roleRepository.findAll();
    }

    /**
     * @see IRoleService#findById(Long)
     */
    @Override
    public Optional findById(Long id) {
        return roleRepository.findById(id);
    }

    /**
     * @see IRoleService#save(Role)
     */
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    /**
     * @see IRoleService#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    /**
     * @see IRoleService#update(Role)
     */
    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }
}
