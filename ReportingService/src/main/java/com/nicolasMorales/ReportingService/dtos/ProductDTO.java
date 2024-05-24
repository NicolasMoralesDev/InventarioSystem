package com.nicolasMorales.ReportingService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para para manipular los datos de los productos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private long codigo;
    private String nombre;
    private String descripcion;
    private String img;
    private String categoria;
    private List<String> subCategoria;
    private String marca;
    private double precio;
    private int cant;
    private boolean borrado;
}
