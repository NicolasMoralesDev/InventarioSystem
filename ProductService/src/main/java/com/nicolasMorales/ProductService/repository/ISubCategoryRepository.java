package com.nicolasMorales.ProductService.repository;

import com.nicolasMorales.ProductService.models.Category;
import com.nicolasMorales.ProductService.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *  @author Nicolas Morales.
 *  Interfaz del Repository de la entidad SubCategoria.
 *  Posee sus metodos JPQL.
 */
@Repository
public interface ISubCategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query("SELECT s FROM SubCategory s WHERE s.titulo = :titulo")
    SubCategory findByTitle(@Param("titulo") String titulo);
}
