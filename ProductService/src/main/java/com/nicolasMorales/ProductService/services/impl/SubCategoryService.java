package com.nicolasMorales.ProductService.services.impl;


import com.nicolasMorales.ProductService.exepciones.BussinesException;
import com.nicolasMorales.ProductService.models.SubCategory;
import com.nicolasMorales.ProductService.repository.ISubCategoryRepository;
import com.nicolasMorales.ProductService.services.ISubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  @author Nicolas Morales.
 *  Implementacion de ISubCategoryService.
 *  Para la servicios de SubCategorias.
 */
@Service
public class SubCategoryService implements ISubCategoryService {

    @Autowired
    private ISubCategoryRepository subCateRepo;

    @Override
    public void createSubCategory(SubCategory nueva) throws BussinesException {
        try {
            if (subCateRepo.findByTitle(nueva.getTitulo()) == null) {
                subCateRepo.save(nueva);
            } else {
                throw new BussinesException("Error, esta subCategoria ya existe!");
            }
        } catch (BussinesException e){
          throw  new BussinesException(e.getMessage());
        }
    }

    @Override
    public List<SubCategory> getAllSubCategory() throws BussinesException {
        try {
            return subCateRepo.findAll();
        } catch (Exception e) {
            throw new BussinesException("Error, se a producido un error!");
        }
    }

    @Override
    public void editSubCategory(SubCategory edit) throws BussinesException {
        try {
            subCateRepo.save(edit);
        } catch (Exception e) {
            throw new BussinesException("Error, se a producido un error!");
        }
    }
}
