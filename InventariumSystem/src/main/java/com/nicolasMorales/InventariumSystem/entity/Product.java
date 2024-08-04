package com.nicolasMorales.InventariumSystem.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Modelado de la entidad producto.
 */
@Entity
@Data
@Table(
        name = "PRODUCTOS",
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
    @JoinColumn(name = "categoria_id")
    private Category categoria;

    private String marca;

    private double precio;

    private int cant;

    private boolean borrado;

}