package com.nicolasMorales.IncomeService.services;

import com.nicolasMorales.IncomeService.dtos.IncomeDTO;
import com.nicolasMorales.IncomeService.dtos.IncomeDTOResponse;
import com.nicolasMorales.IncomeService.dtos.IncomeEditDTO;
import com.nicolasMorales.IncomeService.exceptions.BussinesException;
import com.nicolasMorales.IncomeService.models.Income;

import java.util.List;
import java.util.UUID;

public interface IncomeService {


    public List<IncomeDTOResponse> getAllIncome();

    public Income getIncomeById(UUID id) throws BussinesException;

    public void createIncome(IncomeDTO nuevo) throws BussinesException;

    public String deleteByIdIncome(UUID id);

    public void editIncome(IncomeEditDTO edit) throws BussinesException;
}
