package com.nicolasMorales.InventariumSystem.repository;

import com.nicolasMorales.InventariumSystem.entity.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *  @author Nicolas Morales.
 *  Interfaz del Repository de la entidad UserSec.
 *  Posee sus metodos JPQL.
 */
@Repository
public interface IUserRepository extends JpaRepository<UserSec, Long> {

    Optional findUserEntityByUsername(String username);

}