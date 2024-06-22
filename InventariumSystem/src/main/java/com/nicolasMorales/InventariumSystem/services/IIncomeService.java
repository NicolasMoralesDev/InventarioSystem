package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.dto.IncomeDTO;
import com.nicolasMorales.InventariumSystem.dto.IncomeDTOResponse;
import com.nicolasMorales.InventariumSystem.dto.IncomeEditDTO;
import com.nicolasMorales.InventariumSystem.entity.Income;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;

import java.util.List;
import java.util.UUID;

public interface IIncomeService {


    public List<IncomeDTOResponse> getAllIncome() throws BussinesException;

    public Income getIncomeById(UUID id) throws BussinesException;

    public void createIncome(IncomeDTO nuevo) throws BussinesException;

    public String deleteByIdIncome(UUID id);

    public void editIncome(IncomeEditDTO edit) throws BussinesException;
}
