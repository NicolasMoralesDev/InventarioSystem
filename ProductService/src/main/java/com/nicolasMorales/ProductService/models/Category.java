package com.nicolasMorales.ProductService.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

/**
 *  @author Nicolas Morales
 *  Modelado de la entidad Category
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
