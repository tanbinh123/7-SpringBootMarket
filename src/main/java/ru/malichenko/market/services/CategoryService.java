package ru.malichenko.market.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malichenko.market.entities.Category;
import ru.malichenko.market.repositories.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}

