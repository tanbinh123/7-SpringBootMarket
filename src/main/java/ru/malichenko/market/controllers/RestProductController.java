package ru.malichenko.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.entities.Product;
import ru.malichenko.market.exceptions.ResourceCreationException;
import ru.malichenko.market.exceptions.ResourceNotFoundException;
import ru.malichenko.market.services.ProductService;
import ru.malichenko.market.utils.ProductFilter;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;

    @GetMapping(produces = "application/json")
    public Page<Product> getAllProduct(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                       @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        System.out.println("params = "+params.toString());
        ProductFilter productFilter = new ProductFilter(params);
        return productService.findAll(productFilter.getSpec(), page - 1, 5);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id ));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody @Validated Product p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResourceCreationException("Invalid product");
        }
        if (p.getId() != null && productService.existsById(p.getId())) {
            throw new ResourceCreationException("Product with id: " + p.getId() + "already exist");
        }
        return productService.saveOrUpdate(p);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping
    public void deleteProduct() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
