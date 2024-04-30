package com.nicolasMorales.ProductService.dto;

import com.nicolasMorales.ProductService.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


/**
 *  @author Nicolas Morales
 *  DTO para la Entidad Productos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private UUID id;
    private long codigo;
    private String name;
    private String img;
    private String descripcion;
    private Category categoria;
    private String subCategoria;
    private String marca;
    private int precio;
    private int cant;
    private boolean borrado;
}
