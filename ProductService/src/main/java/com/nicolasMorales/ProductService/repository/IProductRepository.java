package com.nicolasMorales.ProductService.repository;

import com.nicolasMorales.ProductService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductRepository  extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.codigo = :codigo")
    Product findByCodigo(@Param("codigo") int codigo);
}
