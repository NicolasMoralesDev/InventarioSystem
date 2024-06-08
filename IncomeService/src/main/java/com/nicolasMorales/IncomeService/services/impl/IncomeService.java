package com.nicolasMorales.IncomeService.services.impl;

import com.nicolasMorales.IncomeService.dtos.IncomeDTO;
import com.nicolasMorales.IncomeService.dtos.IncomeDTOResponse;
import com.nicolasMorales.IncomeService.dtos.IncomeEditDTO;
import com.nicolasMorales.IncomeService.exceptions.BussinesException;
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
    @Transactional
    @Retry(name = "product-service")
    public void createIncome(IncomeDTO nuevo) throws BussinesException {
        try {

            Income register = new Income();
            List<Long> listProducts = productClient.addProducts(nuevo.getProductos());
            if (listProducts == null) {
                throw new BussinesException("Se ha producido un error al intentar registrar el ingreso!");
            }
            register.setDescription(nuevo.getObservacion());
            register.setProducts(listProducts);
            register.setSupplier(nuevo.getProvedor().getNombre());
            incomeRepo.save(register);

        } catch (BussinesException e){
            throw new BussinesException("Error "+ e.getMessage()) ;
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
            ingreso.setDescription(edit.getObservacion());

        } catch (BussinesException e){
            throw new BussinesException("Error "+ e.getMessage());
        }
    }
}
