package com.nicolasMorales.IncomeService.models;

import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
public class Product {

    private int codigo;

    private String name;

    private String img;

//    @ManyToOne
//    private Category categoria;

    private String subCategoria;

    private String marca;

    private int precio;

    private int cant;
}
