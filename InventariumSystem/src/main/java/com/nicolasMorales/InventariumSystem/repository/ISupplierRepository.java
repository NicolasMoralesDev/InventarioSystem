package com.nicolasMorales.InventariumSystem.repository;

import com.nicolasMorales.InventariumSystem.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz del Repository de la entidad Supplier.
 *  Posee sus metodos JPQL.
 */
@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, UUID> {

    @Query("SELECT s FROM Supplier s WHERE s.nombre = :nombre")
    Supplier findByNombre(@Param("nombre") String nombre);
}
