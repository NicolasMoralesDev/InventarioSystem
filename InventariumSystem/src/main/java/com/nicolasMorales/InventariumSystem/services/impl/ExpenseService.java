package com.nicolasMorales.InventariumSystem.services.impl;

import com.nicolasMorales.InventariumSystem.dto.ExpenseDTO;
import com.nicolasMorales.InventariumSystem.dto.ExpenseDTOResponse;
import com.nicolasMorales.InventariumSystem.entity.Expense;
import com.nicolasMorales.InventariumSystem.entity.Product;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.mapper.ExpenseMapper;
import com.nicolasMorales.InventariumSystem.mapper.ProductsMapper;
import com.nicolasMorales.InventariumSystem.repository.IExpenseRepository;
import com.nicolasMorales.InventariumSystem.services.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ExpenseService implements IExpenseService {

    @Autowired
    private IExpenseRepository expenseRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ExpenseMapper expenseMapper;

    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public void createExpense(ExpenseDTO expense) throws BussinesException {
        try {

            Stream<Product> productList = expense.getProductos().stream().map(producto -> productsMapper.productDTOaProduct(producto));
            List <Long> listProducts = productService.createExpenseProducts(productList.toList());
            Expense egreso = expenseMapper.expenseDTOaExpense(expense);
            if (listProducts == null) {
                throw new BussinesException("Se ha producido un error al intentar registrar el egreso!");
            } else {
                egreso.setProducts(listProducts);
                egreso.setDescription(expense.getObservacion());
                egreso.setUserRegister(egreso.getUserRegister());
                expenseRepository.save(egreso);
            }

        } catch (Exception e) {
            throw new BussinesException("Error "+ e.getMessage());
        }
    }

    @Override
    public List<ExpenseDTOResponse> getAllExpense() throws BussinesException {
        try {
            List <Expense> expenseList = expenseRepository.findAll();
            if (expenseList == null) {
                throw new BussinesException("No se encontraron registros de egresos");
            } else {
                List <ExpenseDTOResponse> expenseDTOResponses = expenseList.stream().map( expense -> expenseMapper.expenseaExpenseDTOResponse(expense) ).toList();
                return expenseDTOResponses;
            }
        } catch (BussinesException e) {
            throw new BussinesException("Error "+ e.getMessage());
        }
    }
}
