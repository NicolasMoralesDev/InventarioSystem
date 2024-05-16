package com.nicolasMorales.IncomeService.services.impl;

import com.nicolasMorales.IncomeService.dtos.IncomeDTO;
import com.nicolasMorales.IncomeService.models.Income;
import com.nicolasMorales.IncomeService.repository.IIncomeRepository;
import com.nicolasMorales.IncomeService.repository.IProductClient;
import com.nicolasMorales.IncomeService.services.IIncomeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

/**
 * @author Nicolas Morales.
 * DTO para la entidad Ingresos.
 */
@Service
public class IncomeService implements IIncomeService {


    @Autowired
    private IIncomeRepository incomeRepo;

    @Autowired
    private IProductClient productClient;

    @Override
    public List<Income> getAllIncome() {
       return incomeRepo.findAll();
    }

    @Override
    public Income getIncomeById(UUID id) {

        return  incomeRepo.findById(id).orElse(null);
    }

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "incomeError")
    @Retry(name = "product-service")
    public String createIncome(IncomeDTO nuevo) {

        try {

            Income register = new Income();
            List<String> listProducts = productClient.addProducts(nuevo.getProducts());
            register.setDescription(nuevo.getDescription());
            register.setProducts(listProducts);
            register.setSuppliers(nuevo.getSuppliers());
            incomeRepo.save(register);
            return "Ingreso registrado!";

        } catch (Exception e){
            return "Error "+ e.getMessage();
        }
    }

    @Override
    public String deleteByIdIncome(UUID id) {

        try {
            incomeRepo.deleteById(id);
            return "Registro Borrado Correctamente!";

        } catch (Exception e){

            return "Error "+ e.getMessage();
        }
    }

    @Override
    public String editIncome(Income edit) {

        try {

            incomeRepo.save(edit);
            return "Registro modificado!";

        } catch (Exception e){
            return "Error "+ e.getMessage();
        }
    }
}
