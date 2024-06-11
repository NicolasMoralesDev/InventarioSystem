package com.nicolasMorales.ProductService.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private long codigo;
    private String nombre;
    @Nullable
    private String descripcion;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category categoria;
    private String marca;
    private double precio;
    private int cant;
    private boolean borrado = false;

}
