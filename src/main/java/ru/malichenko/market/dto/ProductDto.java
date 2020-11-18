package ru.malichenko.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.malichenko.market.entities.ProductEntity;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private int price;
    private String categoryTitle;

    public ProductDto(ProductEntity p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.categoryTitle = p.getCategoryEntity().getTitle();
    }
}
