package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.entity.Category;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;

import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Categoria.
 */
public interface ICategoryService {

    /**
     * Crea una nueva categoria para los productos.
     *
     * @param nuevo {@link Category}
     * @throws BussinesException Error
     */
    void createCategory(Category nuevo) throws BussinesException;

    /**
     * Borra una categoria por id.
     *
     * @param id {@link UUID}
     * @throws BussinesException Error
     */
    void deleteCategory(UUID id) throws BussinesException;

    /**
     * Obtiene todas las categorias..
     *
     * @throws BussinesException Error
     */
    List<Category> getCategorys() throws BussinesException;

    /**
     * Borra una categoria por id.
     *
     * @param id {@link UUID}
     * @throws BussinesException Error
     */
    Category getCategorysById(UUID id) throws BussinesException;

    /**
     * Modifica una categoria.
     *
     * @param edit {@link Category}
     * @throws BussinesException Error
     */
    void modifyCategory(Category edit) throws BussinesException;

}
