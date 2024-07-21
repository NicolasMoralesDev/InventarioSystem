package com.nicolasMorales.InventariumSystem.services.impl;


import com.nicolasMorales.InventariumSystem.entity.Category;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.repository.ICategoryRepository;
import com.nicolasMorales.InventariumSystem.services.ICategoryService;
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

    /**
     * @see ICategoryService#createCategory(Category)
     */
    @Override
    public void createCategory(Category nueva) throws BussinesException {

        try {
            if (cateRepo.findByTitle(nueva.getTitulo()) == null) {
                cateRepo.save(nueva);
            } else {
                throw new BussinesException("Esta categoria ya Existe!");
            }
        } catch (Exception e){
            throw new BussinesException("Error "+e.getMessage());
        }
    }

    /**
     * @see ICategoryService#deleteCategory(UUID)
     */
    @Override
    public void deleteCategory(UUID id) throws BussinesException {

        try {
            cateRepo.deleteById(id);
        } catch (Exception e){
            throw new BussinesException("Error "+ e);
        }
    }

    /**
     * @see ICategoryService#getCategorys()
     */
    @Override
    public List<Category> getCategorys() {
        return cateRepo.findAll();
    }

    /**
     * @see ICategoryService#getCategorysById(UUID)
     */
    @Override
    public Category getCategorysById(UUID id) throws BussinesException {

        try {
            Category category = cateRepo.findById(id).orElse(null);
            if (category == null) {
                throw new BussinesException("Error al obtener la categoria");
            }
            return category;
        } catch (BussinesException e) {
            throw new BussinesException(e.getMessage());
        }
    }

    /**
     * @see ICategoryService#modifyCategory(Category)
     */
    @Override
    public void modifyCategory(Category edit) throws BussinesException {

        try {
            cateRepo.save(edit);
        } catch (Exception e){
            throw new BussinesException("Error "+ e.getMessage());
        }
    }

}
