package com.nicolasMorales.InventariumSystem.repository;

import com.nicolasMorales.InventariumSystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Nicolas Morales.
 *  Interfaz del Repository de la entidad Role.
 *  Posee sus metodos JPQL.
 */
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}