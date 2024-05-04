package com.nicolasMorales.IncomeService.models;

import lombok.Data;

@Data
public class Product {

    private Long codigo;

    private String name;

    private String descipcion;

    private String img;

//    @ManyToOne
//    private Category categoria;

    private String subCategoria;

    private String marca;

    private int precio;

    private int cant;
}
