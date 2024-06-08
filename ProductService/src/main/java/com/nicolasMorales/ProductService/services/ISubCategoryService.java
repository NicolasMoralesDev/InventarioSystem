package com.nicolasMorales.ProductService.services;

import com.nicolasMorales.ProductService.exceptions.BussinesException;
import com.nicolasMorales.ProductService.models.SubCategory;

import java.util.List;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de SubCategoria.
 */
public interface ISubCategoryService {

    void createSubCategory(SubCategory nueva) throws BussinesException;

    List<SubCategory> getAllSubCategory() throws BussinesException;

    void editSubCategory(SubCategory edit) throws BussinesException;
}
