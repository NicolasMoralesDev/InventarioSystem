package com.nicolasMorales.ProductService.dto;

import com.nicolasMorales.ProductService.models.Category;
import com.nicolasMorales.ProductService.models.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    private String img;
    private String descripcion;
    private Category categoria;
    private List<SubCategory> subCategoria;
    private String marca;
    private double precio;
    private int cant;
    private boolean borrado;
}
