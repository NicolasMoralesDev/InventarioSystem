package com.nicolasMorales.IncomeService.models;

import lombok.Data;

/**
 * @author Nicolas Morales.
 * Clase Entidad Producto.
 */
@Data
public class Product {

    private Long codigo;

    private String name;

    private String descripcion;

    private String img;

//    @ManyToOne
//    private Category categoria;

    private String subCategoria;

    private String marca;

    private int precio;

    private int cant;
}
