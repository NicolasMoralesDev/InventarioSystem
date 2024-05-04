package com.nicolasMorales.ProductService.services.impl;


import com.nicolasMorales.ProductService.dto.ProductDTO;
import com.nicolasMorales.ProductService.dto.ProductPaginationDTO;
import com.nicolasMorales.ProductService.mapper.ProductMapper;
import com.nicolasMorales.ProductService.models.Product;
import com.nicolasMorales.ProductService.repository.IProductRepository;
import com.nicolasMorales.ProductService.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Implementacion de IProductoService.
 *  Para la servicios de Productos.
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepo;

    @Autowired
    private ProductMapper productMapper;

    /**
     * Metodo para crear un Producto.
     * @param nuevo Recibe el nuevo producto a crear.
     */
    public void createProduct(Product nuevo) {

            Product producto = productRepo.findByCodigo(nuevo.getCodigo());

            if ( producto == null) {
                productRepo.save(nuevo);

            } else if (producto.getCodigo() == nuevo.getCodigo()){
                producto.setCant(nuevo.getCant()+producto.getCant());
                productRepo.save(producto);
            }
    }

    /**
     * Metodo para crear masivaente productos.
     * @param products Recibe los nuevos productos a crear.
     */
    @Override
    public List<Long> createBulkProducts(List<Product> products) {
           List<Long> listProducts = new ArrayList<>();

           for (Product product : products ){
               this.createProduct(product);
               listProducts.add(product.getCodigo());
           }
           return listProducts;
    }

    /**
     * Metodo para eliminar un producto.
     * @param id Recibe el id del producto.
     */
    @Override
    @Transactional
    public String deleteProduct(UUID id) {
        try {
            productRepo.deleteById(id);
            return "Producto Borrado Correctamente!!";
        } catch (Exception e){
            return  "Error "+ e;
        }
    }

    /**
     * Metodo para eliminar masivaente productos.
     * @param ids Recibe una lista con los ids de los productos.
     */
    @Override
    @Transactional
    @Modifying
    public String deleteProducts(List <UUID> ids) {
        Product producto;

        try {
            for (UUID id : ids){
              producto = productRepo.findById(id).orElse(null);
              producto.setBorrado(true);
            }
            return "Productos Borrados Correctamente!!";
        } catch (Exception e){
            return  "Error "+ e;
        }
    }

    /**
     * Metodo para obtener los productos paginados.
     */
    @Override
    public List <Product> getProducts() {

        List <Product> listProducts = productRepo.findAll();

        return listProducts;
    }

    /**
     * Metodo para obtener un producto por id.
     * @param id Recibe el id del producto.
     */
    @Override
    public Product getProductsById(UUID id) {

        return productRepo.findById(id).orElse(null);
    }

    /**
     * Metodo para editar un producto.
     * @param edit Recibe el producto a editar.
     */
    @Transactional
    @Override
    public String modifyProduct(Product edit) {
        try {
            productRepo.save(edit);
            return "Producto Modificado!";
        } catch (Exception e){
            return "Error "+ e.getMessage();
        }
    }

    /**
     * Metodo para obtener un producto por su codigo de barras.
     * @param code Recibe el codigo de barras del producto.
     */
    @Override
    public ProductDTO getProductsByCode(long code) {
        return productMapper.productToProductDTO(productRepo.findByCodigo(code));

    }
}
