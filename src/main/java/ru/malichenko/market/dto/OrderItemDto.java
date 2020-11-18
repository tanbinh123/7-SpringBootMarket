package ru.malichenko.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.malichenko.market.entities.OrderItemEntity;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;
    private String productTitle;
    private String categoryTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(OrderItemEntity o){
        this.productId = o.getProductEntity().getId();
        this.productTitle =o.getProductEntity().getTitle();
        this.categoryTitle =o.getProductEntity().getCategoryEntity().getTitle();
        this.quantity = o.getQuantity();
        this.price = o.getPrice();
        this.pricePerProduct = o.getPricePerProduct();
    }
}
