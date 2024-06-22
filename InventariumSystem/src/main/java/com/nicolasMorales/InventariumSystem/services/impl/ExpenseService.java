package com.nicolasMorales.InventariumSystem.services.impl;

import com.nicolasMorales.InventariumSystem.entity.Expense;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.repository.IExpenseRepository;
import com.nicolasMorales.InventariumSystem.services.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements IExpenseService {

    @Autowired
    private IExpenseRepository expenseRepository;

    @Override
    public void createExpense(Expense expense) throws BussinesException {
        try {
            expenseRepository.save(expense);
        } catch (Exception e) {
            throw new BussinesException("Error "+ e.getMessage());
        }
    }

    @Override
    public List<Expense> getAllExpense() throws BussinesException {
        try {
            List <Expense> expenseList = expenseRepository.findAll();
            if (expenseList == null) {
                throw new BussinesException("No se encontraron registros de egresos");
            } else {
                return expenseList;
            }
        } catch (BussinesException e) {
            throw new BussinesException("Error "+ e.getMessage());
        }
    }
}
