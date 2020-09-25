package ru.malichenko.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.entities.Product;
import ru.malichenko.market.exceptions.ResourceNotFoundException;
import ru.malichenko.market.utils.ProductFilter;

import java.util.Map;


@Controller
@RequestMapping("/error")
@AllArgsConstructor
public class ErrorController {

    @GetMapping
    public String showErrorPage() {
        return "/err/error";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResourceNotFoundException error(@PathVariable Long id) {
        return new ResourceNotFoundException("Product with id: " + id + " doesn't exists");
    }
}
