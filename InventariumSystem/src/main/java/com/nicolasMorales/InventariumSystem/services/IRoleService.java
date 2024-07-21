package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.entity.Role;

import java.util.List;
import java.util.Optional;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Roles.
 */
public interface IRoleService {

    /**
     * Obtine todos los roles.
     *
     */
    List findAll();

    /**
     * Obtiene un rol por su id.
     *
     * @param id {@link Long}
     */
    Optional findById(Long id);

    /**
     * Registra un nuevo rol.
     *
     * @param role {@link Role}
     */
    Role save(Role role);

    /**
     * Borra un rol por su id.
     *
     * @param id {@link Long}
     */
    void deleteById(Long id);

    /**
     * Edita un rol.
     *
     * @param role {@link Role}
     */
    Role update(Role role);
}