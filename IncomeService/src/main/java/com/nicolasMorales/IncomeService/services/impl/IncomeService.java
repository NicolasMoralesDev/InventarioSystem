package com.nicolasMorales.IncomeService.services.impl;

import com.nicolasMorales.IncomeService.modes.Income;
import com.nicolasMorales.IncomeService.repository.IIncomeRepository;
import com.nicolasMorales.IncomeService.services.IIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IncomeService implements IIncomeService {


    @Autowired
    private IIncomeRepository incomeRepo;

    @Override
    public List<Income> getAllIncome() {
        return null;
    }

    @Override
    public Income getIncomeById(UUID id) {
        return null;
    }

    @Override
    public String createIncome(Income nuevo) {
        return null;
    }

    @Override
    public String deleteByIdIncome(UUID id) {
        return null;
    }

    @Override
    public String editIncome(Income edit) {
        return null;
    }
}
