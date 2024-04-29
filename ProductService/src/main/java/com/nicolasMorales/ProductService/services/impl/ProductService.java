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

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepo;

    @Autowired
    private ProductMapper productMapper;


    public void createProduct(Product nuevo) {

            Product producto = productRepo.findByCodigo(nuevo.getCodigo());

            if ( producto == null) {
                productRepo.save(nuevo);

            } else if (producto.getCodigo() == nuevo.getCodigo()){
                producto.setCant(nuevo.getCant()+producto.getCant());
                productRepo.save(producto);
            }
    }

    @Override
    public List<Long> createBulkProducts(List<Product> products) {
           List<Long> listProducts = new ArrayList<>();

           for (Product product : products ){
               this.createProduct(product);
               listProducts.add(product.getCodigo());
           }
           return listProducts;
    }

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

    @Override
    public ProductPaginationDTO getProducts(int page) {
        Pageable pageable = PageRequest.of( page, 10);
        // crea el listado de productos paginable
        ProductPaginationDTO listProducts = new ProductPaginationDTO();
        // se setean los datos en el ProductPaginationDTO
        listProducts.setPage(page);

        Page<Product> productList = productRepo.findAllPage(pageable);
        List<ProductDTO> productDtoList = productMapper.productListToProductDtoList(productList.getContent());
        listProducts.setProductos(productDtoList);
        listProducts.setTotal(productList.getTotalPages());

        return listProducts;
    }

    @Override
    public Product getProductsById(UUID id) {

        return productRepo.findById(id).orElse(null);
    }

    @Override
    public String modifyProduct(Product nuevo) {

        try {

            productRepo.save(nuevo);
            return "Producto Modificado!";
        } catch (Exception e){
            return "Error "+ e.getMessage();
        }
    }

    @Override
    public ProductDTO getProductsByCode(long code) {
        return productMapper.productToProductDTO(productRepo.findByCodigo(code));

    }
}
