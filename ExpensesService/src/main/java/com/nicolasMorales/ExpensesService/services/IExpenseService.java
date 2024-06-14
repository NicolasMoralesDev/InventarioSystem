package com.nicolasMorales.ExpensesService.services;

import com.nicolasMorales.ExpensesService.exceptions.BussinesException;
import com.nicolasMorales.ExpensesService.models.Expense;

import java.util.List;

public interface IExpenseService {

    void createExpense (Expense expense) throws BussinesException;

    List<Expense> getAllExpense () throws BussinesException;
}
