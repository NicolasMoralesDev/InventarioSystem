package com.nicolasMorales.IncomeService.models;

import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.List;

/**
 * @author Nicolas Morales.
 * Clase Entidad Producto.
 */
@Data
public class Product {

    private Long codigo;
    private String nombre;
    private String descripcion;
    private String img;
    @ElementCollection
    private List<String> categoria;
    @ElementCollection
    private List <String> subCategoria;
    private String marca;
    private double precio;
    private int cant;
}
