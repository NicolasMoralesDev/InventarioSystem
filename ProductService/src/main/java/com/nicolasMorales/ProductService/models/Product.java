package com.nicolasMorales.ProductService.models;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


/**
 *  @author Nicolas Morales
 *  Modelado de la Entidad Producto
 */
@Entity
@Data
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"codigo"})
)
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private long codigo;
    private String name;
    private String descripcion;
    private String img;
    @ManyToOne
    private Category categoria;
    private String subCategoria;
    private String marca;
    private int precio;
    private int cant;
    private boolean borrado;

    public Product(UUID id, long codigo, String name, String img, String subCategoria, String marca, int precio, int cant, boolean borrado, String descripcion) {
        this.id = id;
        this.codigo = codigo;
        this.name = name;
        this.img = img;
        this.subCategoria = subCategoria;
        this.marca = marca;
        this.precio = precio;
        this.cant = cant;
        this.borrado = false;
        this.descripcion = descripcion;
    }
}
