package com.nicolasMorales.InventariumSystem.mapper;

import com.nicolasMorales.InventariumSystem.dto.ExpenseDTO;
import com.nicolasMorales.InventariumSystem.entity.Expense;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Nicolas Morales.
 * Metodos Mapper para mapear Egresos.
 */
@Component
public class ExpenseMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Expense expenseDTOaExpense (ExpenseDTO expenseDTO) {
        Expense egreso = modelMapper.map(expenseDTO, Expense.class);
        return egreso;
    }

}
