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
    public String showAllProducts(Model model, @RequestParam(defaultValue = "1",name ="p") Integer page) {
        if (page < 1) {
            page = 1;
        }
        model.addAttribute("products", productService.findAll(page - 1, 5));
        return "products";
    }
    @GetMapping("/{id}")
    @ResponseBody
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product with id: "+id +" doesn't exists"));
    }
    @GetMapping("/dto/{id}")
    @ResponseBody
    public ProductDto showOneProduct(Model model, @PathVariable Long id) {
        return productService.findDtoById(id).get();
    }
//    @GetMapping
//    @ResponseBody
//    public List<Customer>  firstRequest() {
//        List<Customer> customers = customerRepository.findAll();
////        return  Collections.singletonList(customerRepository.findByName("Bob"));
//        return customers;
//    }
//    @GetMapping
//    @RequestMapping("/market")
//    @ResponseBody
//    public List<Customer>  marketRequest() {
////        List<Customer> customers = customerRepository.findAll();
////        List<Customer> customers = customerRepository.findAllByIdGreaterThan(1L);
////        List<Customer> customers = customerRepository.findAllByIdGreaterThanOrderByIdDesc(1L);
////        return  Collections.singletonList(customerRepository.findByName("Bob"));
////        return  Collections.singletonList(customerRepository.findByNameHql("Bob"));
////        return customers;
//
//    }
}
