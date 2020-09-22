package ru.malichenko.market.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.dto.ProductDto;
import ru.malichenko.market.entities.Product;
import ru.malichenko.market.exceptions.ResourceNotFoundException;
import ru.malichenko.market.services.ProductService;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String showAllProducts(Model model,
                                  @RequestParam(defaultValue = "1", name = "p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        model.addAttribute("products", productService.findAll(page - 1, 5));
        return "products";
    }

    @GetMapping("/filter")
    public String showAllProducts(Model model,
                                  @RequestParam(defaultValue = "21", name = "min") Long min,
                                  @RequestParam(defaultValue = "40", name = "max") Long max) {
        model.addAttribute("products", productService.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(min,max));
        return "products";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
    }

    //этот метод не из дз, можно не смотреть
    @GetMapping("/dto/{id}")
    @ResponseBody
    public ProductDto showOneProduct(Model model, @PathVariable Long id) {
        return productService.findDtoById(id).get();
    }
}
