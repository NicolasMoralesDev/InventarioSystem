package com.nicolasMorales.ReportingService.dtos;

import lombok.Data;

/**
 * DTO para para manipular los datos de los productos.
 */
@Data
public class ProductDTO {

    private long codigo;
    private String nombre;
    private String descripcion;
    private String img;
    private String categoria;
    private String marca;
    private double precio;
    private int cant;
}
