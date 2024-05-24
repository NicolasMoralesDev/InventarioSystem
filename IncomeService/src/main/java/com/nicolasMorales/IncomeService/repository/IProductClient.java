
package com.nicolasMorales.IncomeService.repository;

/**
 *
 * @author Nico Morales
 */

import com.nicolasMorales.IncomeService.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product-service/api/v1")
public interface IProductClient {

    @PostMapping("/product/bulk")
    public List<Long> addProducts (@RequestBody List<Product> productos);
    
}
