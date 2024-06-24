package com.nicolasMorales.InventariumSystem.services.impl;

import com.nicolasMorales.InventariumSystem.dto.IncomeDTO;
import com.nicolasMorales.InventariumSystem.dto.IncomeDTOResponse;
import com.nicolasMorales.InventariumSystem.dto.IncomeEditDTO;
import com.nicolasMorales.InventariumSystem.entity.Income;
import com.nicolasMorales.InventariumSystem.entity.Product;
import com.nicolasMorales.InventariumSystem.entity.Supplier;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.mapper.IncomeMapper;
import com.nicolasMorales.InventariumSystem.mapper.ProductsMapper;
import com.nicolasMorales.InventariumSystem.repository.IIncomeRepository;
import com.nicolasMorales.InventariumSystem.repository.ISupplierRepository;
import com.nicolasMorales.InventariumSystem.services.IIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author Nicolas Morales.
 * DTO para la entidad Ingresos.
 */
@Service
public class IncomeService implements IIncomeService {

    @Autowired
    private IncomeMapper incomeMapper;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private IIncomeRepository incomeRepo;

    @Autowired
    private ISupplierRepository supplierRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<IncomeDTOResponse> getAllIncome() throws BussinesException {
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
    public void createIncome(IncomeDTO nuevo) throws BussinesException {
        try {

            Income register = new Income();
            Stream <Product> productList = nuevo.getProductos().stream().map(producto -> productsMapper.productDTOaProduct(producto));
            Supplier supplier = supplierRepository.findByNombre(nuevo.getProvedor());
            List <Long> listProducts = productService.createBulkProducts(productList.toList());
            if (listProducts == null) {
                throw new BussinesException("Se ha producido un error al intentar registrar el ingreso!");
            } else {
                register.setDescription(nuevo.getObservacion());
                register.setUserRegister(nuevo.getUsuario());
                register.setProducts(listProducts);
                register.setSupplier(supplier);
                incomeRepo.save(register);
            }

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
