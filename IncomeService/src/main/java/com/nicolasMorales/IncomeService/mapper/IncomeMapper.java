package com.nicolasMorales.IncomeService.mapper;

import com.nicolasMorales.IncomeService.dtos.IncomeDTOResponse;
import com.nicolasMorales.IncomeService.models.Income;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicolas Morales.
 * Metodos Mapper para mapear registros de Ingresos.
 */
@Component
public class IncomeMapper {

    /**
     * Metodo para Mappear un Array de Income a un Array IncomeDTOResponse.
     */
    public List<IncomeDTOResponse> incomeListToIncomeDTOList (List <Income> incomes) {
        List<IncomeDTOResponse> incomeList = new ArrayList<>();
        for (Income income : incomes){
            incomeList.add(incomeToIncomeDTO(income));
        }
        return  incomeList;
    }

    /**
     * Metodo para Mappear un Income individual a un IncomeDTOResponse individual.
     */
    public IncomeDTOResponse incomeToIncomeDTO (Income income) {
        IncomeDTOResponse incomeDTOResponse = new IncomeDTOResponse();
        incomeDTOResponse.setDescription(income.getDescription());
        incomeDTOResponse.setFechaIngreso( income.getDateIncome());

        return incomeDTOResponse;
    }
}
