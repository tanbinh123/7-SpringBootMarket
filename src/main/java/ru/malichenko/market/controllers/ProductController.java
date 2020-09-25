package ru.malichenko.market.controllers;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.dto.ProductDto;
import ru.malichenko.market.entities.Product;
import ru.malichenko.market.exceptions.ResourceNotFoundException;
import ru.malichenko.market.services.ProductService;
import ru.malichenko.market.utils.ProductFilter;

import java.util.Map;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String showAllProducts(Model model,
                                  @RequestParam(defaultValue = "1", name = "p") Integer page,
                                  @RequestParam Map<String,String> params) {
        if (page < 1) page = 1;
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> products = productService.findAll(productFilter.getSpec(), page-1, 5);
        model.addAttribute("products", products);
        model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
        //model.addAttribute("products", productService.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice, PageRequest.of(page-1, 4)));
        //model.addAttribute("allProducts", productService.findAllByPriceBetween(minPrice, maxPrice));
        return "products";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id).orElse(null);
        if(product == null) return "redirect:/error/{id}";
        model.addAttribute("product", product);
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product modifiedProduct) {
        productService.saveOrUpdateProduct(modifiedProduct);
        return "redirect:/products/";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add_product_form";
    }



    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        Product deleteProduct = productService.findById(id).orElse(null);
        if(deleteProduct == null) return "redirect:/error/{id}";
        productService.deleteProductById(id);
        return "redirect:/products/";
    }

    //этот метод не из дз, можно не смотреть
    @GetMapping("/dto/{id}")
    @ResponseBody
    public ProductDto showOneProduct(Model model, @PathVariable Long id) {
        return productService.findDtoById(id).get();
    }
}
