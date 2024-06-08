package com.nicolasMorales.IncomeService.mapper;

import com.nicolasMorales.IncomeService.dtos.IncomeDTOResponse;
import com.nicolasMorales.IncomeService.dtos.ProductDTO;
import com.nicolasMorales.IncomeService.models.Income;
import com.nicolasMorales.IncomeService.repository.IProductClient;
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
    private IProductClient productClient;

    /**
     * Metodo para Mappear un Array de Income a un Array IncomeDTOResponse.
     * @param incomes Recibe un listado de ingresos a mappear.
     * @return Devuelve un Array de IncomeDTOResponse.
     */
    public List<IncomeDTOResponse> incomeListToIncomeDTOList (List <Income> incomes) {
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
    public IncomeDTOResponse incomeToIncomeDTO (Income income) {
        IncomeDTOResponse incomeDTOResponse = new IncomeDTOResponse();
        List<ProductDTO> productos = new ArrayList<>();
        incomeDTOResponse.setId(income.getId());
        incomeDTOResponse.setObservacion(income.getDescription());
        incomeDTOResponse.setFechaIngreso(income.getDateIncome());
        incomeDTOResponse.setProvedor(income.getSupplier());
        incomeDTOResponse.setSaldado(income.isSettled());

        for (long codigo : income.getProducts()) {
            productos.add( productClient.getProductByCode(codigo) );
        }
        incomeDTOResponse.setProductos(productos);
        return incomeDTOResponse;
    }
}
