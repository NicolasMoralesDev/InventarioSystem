package com.nicolasMorales.ProductService.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    private String nombre;
    @Nullable
    private String descripcion;
    @Nullable
    private String img;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category categoria;
    @ManyToMany
    private List<SubCategory> subCategoria;
    private String marca;
    private double precio;
    private int cant;
    private boolean borrado;

    public Product (UUID id, long codigo, String nombre, String img, List <SubCategory> subCategoria, String marca, double precio, int cant, boolean borrado, String descripcion) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.img = img;
        this.subCategoria = subCategoria;
        this.marca = marca;
        this.precio = precio;
        this.cant = cant;
        this.borrado = false;
        this.descripcion = descripcion;
    }
}
