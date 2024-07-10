package com.nicolasMorales.InventariumSystem.repository;

import com.nicolasMorales.InventariumSystem.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz del Repository de la entidad Expense.
 *  Posee sus metodos JPQL.
 */
@Repository
public interface IExpenseRepository extends JpaRepository<Expense, UUID> {

    @Override
    @Query("SELECT e FROM Expense e order by e.dateExpense DESC")
    List<Expense> findAll();
}
