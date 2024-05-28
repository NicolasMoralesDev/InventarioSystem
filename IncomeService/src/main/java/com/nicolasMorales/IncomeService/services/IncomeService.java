package com.nicolasMorales.IncomeService.services;

import com.nicolasMorales.IncomeService.dtos.IncomeDTO;
import com.nicolasMorales.IncomeService.dtos.IncomeDTOResponse;
import com.nicolasMorales.IncomeService.dtos.IncomeEditDTO;
import com.nicolasMorales.IncomeService.excepciones.BussinesException;
import com.nicolasMorales.IncomeService.models.Income;

import java.util.List;
import java.util.UUID;

public interface IncomeService {


    public List<IncomeDTOResponse> getAllIncome();

    public Income getIncomeById(UUID id) throws BussinesException;

    public String createIncome(IncomeDTO nuevo);

    public String deleteByIdIncome(UUID id);

    public void editIncome(IncomeEditDTO edit) throws BussinesException;
}
