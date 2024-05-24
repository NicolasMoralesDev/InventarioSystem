package com.nicolasMorales.IncomeService.dtos;

import com.nicolasMorales.IncomeService.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Nicolas Morales.
 * DTO para la entidad Ingresos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDTO {

    private String description;
    private List <String> suppliers;
    private List <Product> products;
    private String fechaIngreso;

}
