package ru.malichenko.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.entities.Product;
import ru.malichenko.market.services.ProductService;
import ru.malichenko.market.utils.ProductFilter;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;

    @GetMapping
    public Page<Product> getAllProduct
            (
             @RequestParam(defaultValue = "1", name = "p") Integer page
            ,@RequestParam Map<String,String> params
    ){

        ProductFilter productFilter = new ProductFilter(params);
        return productService.findAll(productFilter.getSpec(), page-1, 5);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id).get();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product p){
        p.setId(null);
        return productService.saveOrUpdate(p);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product p){
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping
    public void deleteProduct(){
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteProductById(id);
    }
}
