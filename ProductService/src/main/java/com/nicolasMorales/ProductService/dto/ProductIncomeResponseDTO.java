package com.nicolasMorales.ProductService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductIncomeResponseDTO {

    private long codigo;
    private String nombre;
}
