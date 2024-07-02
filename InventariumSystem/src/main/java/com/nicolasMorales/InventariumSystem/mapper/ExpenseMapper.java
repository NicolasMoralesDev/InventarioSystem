package com.nicolasMorales.InventariumSystem.mapper;

import com.nicolasMorales.InventariumSystem.dto.ExpenseDTO;
import com.nicolasMorales.InventariumSystem.dto.ExpenseDTOResponse;
import com.nicolasMorales.InventariumSystem.dto.ProductDTO;
import com.nicolasMorales.InventariumSystem.entity.Expense;
import com.nicolasMorales.InventariumSystem.entity.Product;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.services.impl.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Nicolas Morales.
 * Metodos Mapper para mapear Egresos.
 */
@Component
public class ExpenseMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductsMapper productsMapper;

    /**
     * Metodo para Mappear un Expense individual a un ExpenseDTO individual.
     * @param expenseDTO Recibe un egreso individual a mappear.
     * @return Devuelve ExpenseDTO.
     */
    public Expense expenseDTOaExpense (ExpenseDTO expenseDTO) {
        Expense egreso = modelMapper.map(expenseDTO, Expense.class);
        return egreso;
    }

    /**
     * Metodo para Mappear un Expense individual a un ExpenseDTOResponse individual.
     * @param expense Recibe un egreso individual a mappear.
     * @return Devuelve ExpenseDTOResponse.
     */
    public ExpenseDTOResponse expenseaExpenseDTOResponse (Expense expense) {
        modelMapper.typeMap(Expense.class, ExpenseDTOResponse.class)
                .addMapping(Expense::getDateExpense, ExpenseDTOResponse::setFechaEgreso)
                .addMapping(Expense::getUserRegister, ExpenseDTOResponse::setUsuario)
                .addMapping(Expense::getDescription, ExpenseDTOResponse::setObservacion)
                .addMapping(Expense::getId, ExpenseDTOResponse::setId);

        List<ProductDTO> productList = expense.getProducts().stream().map(codigo -> {
            try {
                return productService.getProductByCodeReg(codigo);
            } catch (BussinesException e) {
                throw new RuntimeException(e);
            }
        }).toList();

        ExpenseDTOResponse egreso = modelMapper.map(expense, ExpenseDTOResponse.class);

        egreso.setProductos(productList);
        return egreso;
    }

}
