package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.dto.ExpenseDTO;
import com.nicolasMorales.InventariumSystem.dto.ExpenseDTOResponse;
import com.nicolasMorales.InventariumSystem.entity.Expense;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;

import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Egresos.
 */
public interface IExpenseService {

    /**
     * Registra un nuevo egreso.
     *
     * @param expense {@link ExpenseDTO}
     * @throws BussinesException Error
     */
    void createExpense (ExpenseDTO expense) throws BussinesException;

    /**
     * Obtiene todos los egresos.
     *
     * @throws BussinesException Error
     */
    List<ExpenseDTOResponse> getAllExpense () throws BussinesException;
}
