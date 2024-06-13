package com.nicolasMorales.ExpensesService.repository;

import com.nicolasMorales.ExpensesService.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz con los metodos JPA para los egresos.
 */
@Repository
public interface IExpenseRepository extends JpaRepository<Expense, UUID> {
}
