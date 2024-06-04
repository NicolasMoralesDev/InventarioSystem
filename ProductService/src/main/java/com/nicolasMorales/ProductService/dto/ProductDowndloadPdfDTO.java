package com.nicolasMorales.ProductService.dto;

import lombok.Data;

import java.util.List;

/**
 *  @author Nicolas Morales.
 *  DTO para enviar informacion al servidor de reportes.
 */
@Data
public class ProductDowndloadPdfDTO {

    private long codigo;
    private String nombre;
    private String descripcion;
    private String img;
    private String categoria;
    private String marca;
    private double precio;
    private int cant;

}
