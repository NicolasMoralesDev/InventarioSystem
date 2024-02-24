package com.nicolasMorales.ProductService.repository;

import com.nicolasMorales.ProductService.models.Category;
import com.nicolasMorales.ProductService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT c FROM Category c WHERE c.title = :title")
    Category findByTitle(@Param("title") String title);
}
