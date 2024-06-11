package com.nicolasMorales.IncomeService.repository;

import com.nicolasMorales.IncomeService.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz con los metodos JPA para los Ingresos.
 */
@Repository
public interface IIncomeRepository extends JpaRepository<Income, UUID> {

    @Override
    @Query("SELECT i FROM Income i order by i.dateIncome DESC")
    List<Income> findAll();
}
