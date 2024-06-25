package com.nicolasMorales.InventariumSystem.repository;

import com.nicolasMorales.InventariumSystem.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz del Repository de la entidad Income.
 *  Posee sus metodos JPQL.
 */
@Repository
public interface IIncomeRepository extends JpaRepository<Income, UUID> {

    @Override
    @Query("SELECT i FROM Income i order by i.dateIncome DESC")
    List<Income> findAll();
}
