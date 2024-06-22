package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.entity.Category;

import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Categoria.
 */
public interface ICategoryService {

    String createCategory(Category nuevo);

    String deleteCategory(UUID id);

    List<Category> getCategorys();

    Category getCategorysById(UUID id);

    String modifyCategory(Category edit);

}
