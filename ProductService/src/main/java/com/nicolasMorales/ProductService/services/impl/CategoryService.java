package com.nicolasMorales.ProductService.services.impl;

import com.nicolasMorales.ProductService.models.Category;
import com.nicolasMorales.ProductService.repository.ICategoryRepository;
import com.nicolasMorales.ProductService.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Implementacion de ICategoryService.
 *  Para la servicios de Categoria.
 */
@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository cateRepo;

    @Override
    public String createCategory(Category nueva) {

        try {
            if (cateRepo.findByTitle(nueva.getTitle()) == null) {

                cateRepo.save(nueva);
                return "Categoria agregada!!";
            } else {
                return  "Esta categoria ya Existe!";
            }

        } catch (Exception e){
            return  "Error "+ e;
        }
    }

    @Override
    public String deleteCategory(UUID id) {

        try {
            cateRepo.deleteById(id);

            return "Producto Borrado Correctamente!!";
        } catch (Exception e){
            return  "Error "+ e;
        }

    }

    @Override
    public List<Category> getCategorys() {

        return  cateRepo.findAll();

    }

    @Override
    public Category getCategorysById(UUID id) {
        return cateRepo.findById(id).orElse(null);

    }

    @Override
    public String modifyCategory(Category edit) {

        try {

            cateRepo.save(edit);
            return "Categoria Modificada!";
        } catch (Exception e){
            return "Error "+ e.getMessage();
        }
    }

}
