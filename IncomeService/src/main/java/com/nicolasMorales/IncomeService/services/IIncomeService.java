package com.nicolasMorales.IncomeService.services;

import com.nicolasMorales.IncomeService.modes.Income;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public interface IIncomeService {


    public List<Income> getAllIncome();

    public Income getIncomeById(UUID id);

    public String createIncome(Income nuevo);

    public String deleteByIdIncome(UUID id);

    public String editIncome(Income edit);
}
