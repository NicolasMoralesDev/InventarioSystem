package com.nicolasMorales.IncomeService.services.impl;

import com.nicolasMorales.IncomeService.dtos.IncomeDTO;
import com.nicolasMorales.IncomeService.dtos.IncomeDTOResponse;
import com.nicolasMorales.IncomeService.dtos.IncomeEditDTO;
import com.nicolasMorales.IncomeService.excepciones.BussinesException;
import com.nicolasMorales.IncomeService.mapper.IncomeMapper;
import com.nicolasMorales.IncomeService.models.Income;
import com.nicolasMorales.IncomeService.repository.IIncomeRepository;
import com.nicolasMorales.IncomeService.repository.IProductClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Nicolas Morales.
 * DTO para la entidad Ingresos.
 */
@Service
public class IncomeService implements com.nicolasMorales.IncomeService.services.IncomeService {

    @Autowired
    private IncomeMapper incomeMapper;

    @Autowired
    private IIncomeRepository incomeRepo;

    @Autowired
    private IProductClient productClient;

    @Override
    public List<IncomeDTOResponse> getAllIncome() {
       return incomeMapper.incomeListToIncomeDTOList(incomeRepo.findAll());
    }

    @Override
    public Income getIncomeById(UUID id) throws BussinesException {
        try {
            Income registro = incomeRepo.findById(id).orElse(null);
            if (registro == null) {
                throw new BussinesException("No se encontro ningun registro con el ID : "+ id);
            } else {
                return registro;
            }
        } catch (BussinesException e) {
            throw new BussinesException("Error "+ e.getMessage());
        }
    }

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "incomeError")
    @Transactional
    @Retry(name = "product-service")
    public String createIncome(IncomeDTO nuevo) {
        try {

            Income register = new Income();
            List<Long> listProducts = productClient.addProducts(nuevo.getProducts());
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

    @Transactional
    @Override
    public void editIncome(IncomeEditDTO edit) throws BussinesException {
        try {
            Income ingreso = this.getIncomeById(edit.getId());
            ingreso.setId(edit.getId());
            ingreso.setDescription(edit.getDescripcion());

        } catch (BussinesException e){
            throw new BussinesException("Error "+ e.getMessage());
        }
    }
}
