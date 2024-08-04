package com.nicolasMorales.InventariumSystem.services.impl;

import com.nicolasMorales.InventariumSystem.controllers.categorias.ControllerCategory;
import com.nicolasMorales.InventariumSystem.dto.ExpenseDTO;
import com.nicolasMorales.InventariumSystem.dto.ExpenseDTOResponse;
import com.nicolasMorales.InventariumSystem.entity.Expense;
import com.nicolasMorales.InventariumSystem.entity.Product;
import com.nicolasMorales.InventariumSystem.entity.UserSec;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.mapper.ExpenseMapper;
import com.nicolasMorales.InventariumSystem.mapper.ProductsMapper;
import com.nicolasMorales.InventariumSystem.repository.IExpenseRepository;
import com.nicolasMorales.InventariumSystem.repository.IUserRepository;
import com.nicolasMorales.InventariumSystem.services.IExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ExpenseService implements IExpenseService {

    private static Logger logger = LoggerFactory.getLogger(ControllerCategory.class);

    @Autowired
    private IExpenseRepository expenseRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ExpenseMapper expenseMapper;

    @Autowired
    private ProductsMapper productsMapper;

    /**
     * @see IExpenseService#createExpense(ExpenseDTO)
     */
    @Override
    public void createExpense(ExpenseDTO expense) throws BussinesException {
        try {
            logger.info("Registrando nuevo egreso...");
            UserSec usuario = (UserSec) userRepository.findUserEntityByUsername(expense.getUsuario()).orElse(null);
            Stream<Product> productList = expense.getProductos().stream().map(producto -> productsMapper.productDTOaProduct(producto));
            List <Long> listProducts = productService.createExpenseProducts(productList.toList());
            Expense egreso = expenseMapper.expenseDTOaExpense(expense);

            if (listProducts == null) {
                throw new BussinesException("Se ha producido un error al intentar registrar el egreso!");
            } else {
                egreso.setProducts(listProducts);
                egreso.setDescription(expense.getObservacion());
                egreso.setUserRegister(usuario);
                expenseRepository.save(egreso);
            }
        } catch (Exception e) {
            throw new BussinesException("Error "+ e.getMessage());
        }
    }

    /**
     * @see IExpenseService#getAllExpense()
     */
    @Override
    public List<ExpenseDTOResponse> getAllExpense() throws BussinesException {
        try {
            logger.info("Obteniendo registros de egresos...");
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
