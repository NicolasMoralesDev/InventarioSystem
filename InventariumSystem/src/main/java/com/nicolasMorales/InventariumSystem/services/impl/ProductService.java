package com.nicolasMorales.InventariumSystem.services.impl;

import com.nicolasMorales.InventariumSystem.dto.ProductDTO;
import com.nicolasMorales.InventariumSystem.entity.Product;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.mapper.ProductsMapper;
import com.nicolasMorales.InventariumSystem.repository.IProductRepository;
import com.nicolasMorales.InventariumSystem.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private ProductsMapper productMapper;

    @Autowired
    private PdfService pdfService;

    /**
     * Metodo para crear un Producto.
     * @param nuevo Recibe el nuevo producto a crear.
     */
    @Transactional
    @Override
    public void createProduct(Product nuevo) throws BussinesException {
        try {
            Product producto = productRepo.findByCodigo(nuevo.getCodigo());
            if ( producto == null) {
                productRepo.save(nuevo);
            } else if (producto.getCodigo() == nuevo.getCodigo()){
                producto.setCant(nuevo.getCant()+producto.getCant());
                productRepo.save(producto);
            }
        } catch ( Exception  e) {
            throw new BussinesException("Error, No se pudo cargar el producto: " + nuevo.getNombre());
        }
    }

    /**
     * Metodo para crear masivaente productos.
     * @param products Recibe los nuevos productos a crear.
     */
    @Override
    @Transactional
    public List<Long> createBulkProducts(List<Product> products) throws BussinesException {

        List<Long> listProducts = new ArrayList<>();
        for (Product product : products ){
            try {
                this.createProduct(product);
                listProducts.add(product.getCodigo());
            } catch (BussinesException e){
                throw new BussinesException("Error, No se pudo cargar el producto: " + product.getNombre());
            }
        }
        return listProducts;
    }

    /**
     * Metodo para eliminar un producto.
     * @param id Recibe el id del producto.
     */
    @Override
    @Transactional
    public void deleteProduct(UUID id) throws BussinesException{
        try {
            productRepo.deleteById(id);
            Product producto = this.getProductsById(id);

            if (producto == null) {
                throw new BussinesException("Error");
            }
            producto.setBorrado(true);
        } catch (Exception e){
            throw new BussinesException(e.getMessage());
        }
    }

    /**
     * Metodo para eliminar masivaente productos.
     * @param ids Recibe una lista con los ids de los productos.
     */
    @Override
    @Transactional
    @Modifying
    public void deleteProducts(List <UUID> ids) throws BussinesException{
        Product producto;
        try {
            for (UUID id : ids){
                producto = productRepo.findById(id).orElse(null);
                if (producto == null) {
                    throw new BussinesException("El ID del producto es invaido!");
                }
                producto.setBorrado(true);
            }
        } catch (BussinesException e){
            throw new BussinesException("Error "+ e);
        }
    }

    /**
     * Metodo para obtener los productos paginados.
     */
    @Override
    public List <Product> getProducts() throws BussinesException{
        try {
            List <Product> listProducts = productRepo.findAll();
            if (listProducts == null) {
                throw new BussinesException("No se encontraron Productos cargados!!");
            }
            return listProducts;
        } catch (BussinesException e) {
            throw new BussinesException(e.getMessage());
        }
    }

    /**
     * Metodo para obtener un producto por id.
     * @param id Recibe el id del producto.
     */
    @Override
    public Product getProductsById(UUID id) throws BussinesException {
        try {
            Product producto = productRepo.findById(id).orElse(null);
            if (producto == null) {
                throw new BussinesException("El ID: "+ id + "es invalido.");
            }

            return producto;
        } catch (BussinesException e) {
            throw new BussinesException(e.getMessage());
        }
    }

    /**
     * Metodo para editar un producto.
     * @param edit Recibe el producto a editar.
     */
    @Override
    public void modifyProduct(Product edit) throws BussinesException {
        try {
            productRepo.save(edit);
        } catch (Exception e){
            throw new BussinesException("Se ha producido un error");
        }
    }

    /**
     * Metodo para obtener un producto por su codigo de barras.
     * @param code Recibe el codigo de barras del producto.
     */
    @Override
    public ProductDTO getProductsByCode(long code) throws BussinesException {
        try {
            Product product = productRepo.findByCodigo(code);
            if (product != null) {
                return productMapper.productaProductDTO(product);
            } else {
                throw new BussinesException("No se encontro ningun producto asociado al codigo: " + code);
            }
        } catch (BussinesException e) {
            throw new BussinesException("Error " + e.getMessage());
        }
    }

    @Override
    public HashMap<String, String> downloadPDF(List<UUID> productosIds) throws BussinesException {
        return null;
    }

//    @Override
//    public HashMap<String, String> downloadPDF(List<UUID> productosIds) throws BussinesException {
//        try {
//            List<ProductDTO> productList = new ArrayList<>();
//            for(UUID productID : productosIds) {
//                productList.add(productMapper.productaProductDTO(this.getProductsById(productID)));
//            }
//            return pdfService.generatePdfProductos(productList);
//        } catch (BussinesException e) {
//            throw new BussinesException(e.getMessage());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
