package com.nicolasMorales.InventariumSystem.mapper;

import com.nicolasMorales.InventariumSystem.dto.IncomeDTOResponse;
import com.nicolasMorales.InventariumSystem.dto.ProductDTO;
import com.nicolasMorales.InventariumSystem.entity.Income;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicolas Morales.
 * Metodos Mapper para mapear registros de Ingresos.
 */
@Component
public class IncomeMapper {

    @Autowired
    private ProductService productService;

    /**
     * Metodo para Mappear un Array de Income a un Array IncomeDTOResponse.
     * @param incomes Recibe un listado de ingresos a mappear.
     * @return Devuelve un Array de IncomeDTOResponse.
     */
    public List<IncomeDTOResponse> incomeListToIncomeDTOList (List <Income> incomes) throws BussinesException {
        List<IncomeDTOResponse> incomeList = new ArrayList<>();
        for (Income income : incomes){
            incomeList.add(incomeToIncomeDTO(income));
        }
        return  incomeList;
    }

    /**
     * Metodo para Mappear un Income individual a un IncomeDTOResponse individual.
     * @param income Recibe un ingreso individual a mappear.
     * @return Devuelve IncomeDTOResponse.
     */
    public IncomeDTOResponse incomeToIncomeDTO (Income income) throws BussinesException {
        IncomeDTOResponse incomeDTOResponse = new IncomeDTOResponse();
        List<ProductDTO> productos = new ArrayList<>();
        incomeDTOResponse.setId(income.getId());
        incomeDTOResponse.setObservacion(income.getDescription());
        incomeDTOResponse.setFechaIngreso(income.getDateIncome());
        incomeDTOResponse.setProvedor(income.getSupplier().getNombre());
        incomeDTOResponse.setSaldado(income.isSettled());
        incomeDTOResponse.setUsuario(income.getUserRegister().getNombreCompleto());

        for (long codigo : income.getProducts()) {
            productos.add( productService.getProductByCodeReg(codigo) );
        }
        incomeDTOResponse.setProductos(productos);
        return incomeDTOResponse;
    }
}
