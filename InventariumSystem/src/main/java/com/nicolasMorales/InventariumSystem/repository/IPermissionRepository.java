package com.nicolasMorales.InventariumSystem.repository;

import com.nicolasMorales.InventariumSystem.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Nicolas Morales.
 *  Interfaz del Repository de la entidad Permission.
 *  Posee sus metodos JPQL.
 */
@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
}