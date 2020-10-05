package ru.malichenko.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.entities.Product;
import ru.malichenko.market.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.findAll(Specification.where(null),0,10).getContent();
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
