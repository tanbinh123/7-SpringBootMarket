package ru.malichenko.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malichenko.market.entities.CategoryEntity;
import ru.malichenko.market.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }
}

