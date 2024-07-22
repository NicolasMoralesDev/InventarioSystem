package com.nicolasMorales.InventariumSystem.services.impl;


import com.nicolasMorales.InventariumSystem.controllers.categorias.ControllerCategory;
import com.nicolasMorales.InventariumSystem.entity.Category;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.repository.ICategoryRepository;
import com.nicolasMorales.InventariumSystem.services.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(ControllerCategory.class);

    @Autowired
    private ICategoryRepository cateRepo;

    /**
     * @see ICategoryService#createCategory(Category)
     */
    @Override
    public void createCategory(Category nueva) throws BussinesException {

        try {
            logger.info("Creando categoria...");
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
            logger.info("Borrando categoria con id: {}", id);
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
            logger.info("Obteniendo categoria con id: {}", id);
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
            logger.info("Editando categoria...");
            cateRepo.save(edit);
        } catch (Exception e){
            throw new BussinesException("Error "+ e.getMessage());
        }
    }

}
