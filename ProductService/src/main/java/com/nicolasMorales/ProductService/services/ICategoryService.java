package com.nicolasMorales.ProductService.services;

import com.nicolasMorales.ProductService.models.Category;
import com.nicolasMorales.ProductService.models.Product;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {

        String createCategory(Category nuevo);

        String deleteCategory(UUID id);

        List<Category> getCategorys();

        Category getCategorysById(UUID id);

        String modifyCategory(Category edit);

}
