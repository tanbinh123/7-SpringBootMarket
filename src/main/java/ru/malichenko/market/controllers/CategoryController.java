package ru.malichenko.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malichenko.market.entities.CategoryEntity;
import ru.malichenko.market.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping(produces = "application/json")
    public List<CategoryEntity> getAllCategories() {
        return categoryService.findAll();
    }

}
