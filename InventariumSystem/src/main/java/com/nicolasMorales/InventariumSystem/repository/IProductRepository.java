package com.nicolasMorales.InventariumSystem.repository;

import com.nicolasMorales.InventariumSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz del Repository de la entidad Producto.
 *  Posee sus metodos JPQL.
 */
@Repository
public interface IProductRepository  extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.codigo = :codigo and p.borrado = false")
    Product findByCodigo(@Param("codigo") long codigo);

    @Override
    @Query("SELECT p FROM Product p WHERE p.borrado = false")
    List<Product> findAll();

    @Query("UPDATE Product p SET p.borrado = true WHERE p.id = :id ")
    void findDeleteSoft(@Param("id") UUID id);
}
