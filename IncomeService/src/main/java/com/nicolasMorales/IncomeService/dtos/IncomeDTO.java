package com.nicolasMorales.IncomeService.dtos;

import com.nicolasMorales.IncomeService.models.Product;
import lombok.Data;

import java.util.List;

/**
 * @author Nicolas Morales.
 * DTO para la entidad Ingresos.
 */
@Data
public class IncomeDTO {

    private String description;

    private List <String> suppliers;

    private List <Product> products;
}
