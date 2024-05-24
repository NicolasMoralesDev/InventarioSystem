package com.nicolasMorales.IncomeService.services;

import com.nicolasMorales.IncomeService.dtos.IncomeDTO;
import com.nicolasMorales.IncomeService.dtos.IncomeDTOResponse;
import com.nicolasMorales.IncomeService.models.Income;

import java.util.List;
import java.util.UUID;

public interface IIncomeService {


    public List<IncomeDTOResponse> getAllIncome();

    public Income getIncomeById(UUID id);

    public String createIncome(IncomeDTO nuevo);

    public String deleteByIdIncome(UUID id);

    public String editIncome(Income edit);
}
