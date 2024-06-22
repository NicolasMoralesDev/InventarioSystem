package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.entity.Expense;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;

import java.util.List;

public interface IExpenseService {

    void createExpense (Expense expense) throws BussinesException;

    List<Expense> getAllExpense () throws BussinesException;
}
