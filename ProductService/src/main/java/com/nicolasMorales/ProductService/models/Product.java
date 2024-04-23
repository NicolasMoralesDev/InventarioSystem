package com.nicolasMorales.ProductService.models;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"codigo"})
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private long codigo;

    private String name;

    private String img;

//    @ManyToOne
//    private Category categoria;

    private String subCategoria;

    private String marca;

    private int precio;

    private int cant;


}
