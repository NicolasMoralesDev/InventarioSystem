package com.nicolasMorales.InventariumSystem.services.impl;

import com.nicolasMorales.InventariumSystem.controllers.categorias.ControllerCategory;
import com.nicolasMorales.InventariumSystem.dto.IncomeDTO;
import com.nicolasMorales.InventariumSystem.dto.IncomeDTOResponse;
import com.nicolasMorales.InventariumSystem.dto.IncomeEditDTO;
import com.nicolasMorales.InventariumSystem.entity.Income;
import com.nicolasMorales.InventariumSystem.entity.Product;
import com.nicolasMorales.InventariumSystem.entity.Supplier;
import com.nicolasMorales.InventariumSystem.entity.UserSec;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.mapper.IncomeMapper;
import com.nicolasMorales.InventariumSystem.mapper.ProductsMapper;
import com.nicolasMorales.InventariumSystem.repository.IIncomeRepository;
import com.nicolasMorales.InventariumSystem.repository.ISupplierRepository;
import com.nicolasMorales.InventariumSystem.repository.IUserRepository;
import com.nicolasMorales.InventariumSystem.services.IIncomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(ControllerCategory.class);

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

    @Autowired
    private IUserRepository userRepository;

    /**
     * @see IIncomeService#getAllIncome()
     */
    @Override
    public List<IncomeDTOResponse> getAllIncome() throws BussinesException {
        logger.info("Obteniendo registros de ingresos...");
        return incomeMapper.incomeListToIncomeDTOList(incomeRepo.findAll());
    }

    /**
     * @see IIncomeService#getIncomeById(UUID)
     */
    @Override
    public Income getIncomeById(UUID id) throws BussinesException {
        try {
            logger.info("Obteniendo registro de ingreso con id: {}", id);
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

    /**
     * @see IIncomeService#createIncome(IncomeDTO)
     */
    @Override
    @Transactional
    public void createIncome(IncomeDTO nuevo) throws BussinesException {
        try {
            logger.info("Registrando ingreso...");
            Income register = new Income();
            Stream <Product> productList = nuevo.getProductos().stream().map(producto -> productsMapper.productDTOaProduct(producto));
            Supplier supplier = supplierRepository.findByNombre(nuevo.getProvedor());
            UserSec usuario = (UserSec) userRepository.findUserEntityByUsername(nuevo.getUsuario()).orElse(null);
            List <Long> listProducts = productService.createBulkProducts(productList.toList());

            if (listProducts == null) {
                throw new BussinesException("Se ha producido un error al intentar registrar el ingreso!");
            } else {
                register.setDescription(nuevo.getObservacion());
                register.setUserRegister(usuario);
                register.setProducts(listProducts);
                register.setSupplier(supplier);
                incomeRepo.save(register);
            }

        } catch (BussinesException e){
            throw new BussinesException("Error "+ e.getMessage()) ;
        }
    }

    /**
     * @see IIncomeService#deleteByIdIncome(UUID)
     */
    @Override
    public String deleteByIdIncome(UUID id) {
        try {
            logger.info("Borrando registro de ingreso con id: {}", id);
            incomeRepo.deleteById(id);
            return "Registro Borrado Correctamente!";
        } catch (Exception e){
            return "Error "+ e.getMessage();
        }
    }

    /**
     * @see IIncomeService#editIncome(IncomeEditDTO)
     */
    @Transactional
    @Override
    public void editIncome(IncomeEditDTO edit) throws BussinesException {
        try {
            logger.info("Editando registro de ingreso...");
            Income ingreso = this.getIncomeById(edit.getId());
            ingreso.setId(edit.getId());
            ingreso.setDescription(edit.getObservacion());

        } catch (BussinesException e){
            throw new BussinesException("Error "+ e.getMessage());
        }
    }
}
