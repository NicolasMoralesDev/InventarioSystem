package com.nicolasMorales.ProductService.repository;

import com.nicolasMorales.ProductService.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductRepository  extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.codigo = :codigo and p.borrado = false")
    Product findByCodigo(@Param("codigo") long codigo);
    @Query("SELECT p FROM Product p WHERE p.borrado = false")
    Page<Product> findAllPage(Pageable pageable);
    @Query("UPDATE Product p SET p.borrado = true WHERE p.id = :id ")
    void findDeleteSoft(@Param("id") UUID id);
}
