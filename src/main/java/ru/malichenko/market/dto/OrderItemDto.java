package ru.malichenko.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.malichenko.market.entities.OrderItem;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(OrderItem o){
        this.productId = o.getProduct().getId();
        this.productTitle =o.getProduct().getTitle();
        this.quantity = o.getQuantity();
        this.price = o.getPrice();
        this.pricePerProduct = o.getPricePerProduct();
    }
}
