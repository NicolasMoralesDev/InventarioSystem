package com.nicolasMorales.ProductService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/*
  DTO para devolver un producto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    public UUID id;

    private int codigo;

    private String name;

    private String img;

//    private Category categoria;

    private String subCategoria;

    private String marca;

    private int precio;

    private int cant;
}
