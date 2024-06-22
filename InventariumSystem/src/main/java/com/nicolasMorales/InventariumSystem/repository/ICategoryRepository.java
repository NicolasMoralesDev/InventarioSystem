package com.nicolasMorales.InventariumSystem.repository;

import com.nicolasMorales.InventariumSystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz del Repository de la entidad Category.
 *  Posee sus metodos JPQL.
 */
@Repository
public interface ICategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT c FROM Category c WHERE c.titulo = :titulo")
    Category findByTitle(@Param("titulo") String titulo);
}