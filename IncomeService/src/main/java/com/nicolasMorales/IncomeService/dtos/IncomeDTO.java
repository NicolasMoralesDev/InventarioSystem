package com.nicolasMorales.IncomeService.dtos;

import com.nicolasMorales.IncomeService.models.Product;
import lombok.Data;

import java.util.List;


@Data
public class IncomeDTO {

    private String description;

    private String suppliers;

    private List <Product> products;
}
