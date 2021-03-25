package ru.malichenko.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.malichenko.market.entities.CategoryEntity;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;

    public CategoryDto(CategoryEntity categoryEntity) {
        this.id = categoryEntity.getId();
        this.title = categoryEntity.getTitle();
    }
}
