package com.nicolasMorales.InventariumSystem.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Modelado de la entidad categoria.
 */
@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titulo;

    @Nullable
    private String descripcion;

    private boolean borrado;

}
