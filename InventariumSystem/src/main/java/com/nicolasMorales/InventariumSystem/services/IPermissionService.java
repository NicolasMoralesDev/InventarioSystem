package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.entity.Permission;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Permisos.
 */
public interface IPermissionService {

    /**
     * Obtiene todos los permisos.
     *
     */
    List findAll ();

    /**
     * Obtiene un permiso por su id.
     *
     * @param id {@link Long}
     */
    Optional findById (Long id);

    /**
     * Crea un nuevo permiso.
     *
     * @param permission {@link Permission}
     */
    Permission save (Permission permission);

    /**
     * Borra un permiso por su id.
     *
     * @param id {@link Long}
     */
    void deleteById (Long id);

    /**
     * Edita un permiso.
     *
     * @param permission {@link Permission}
     */
    Permission update (Permission permission);
}
