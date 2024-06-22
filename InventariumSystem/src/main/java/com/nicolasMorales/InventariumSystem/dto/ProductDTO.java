package com.nicolasMorales.InventariumSystem.dto;

import com.nicolasMorales.InventariumSystem.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  DTO para la Entidad Productos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private UUID id;
    private long codigo;
    private String nombre;
    private String descripcion;
    private Category categoria;
    private String marca;
    private double precio;
    private int cant;
    private boolean borrado;
}
