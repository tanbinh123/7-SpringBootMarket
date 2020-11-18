package ru.malichenko.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.malichenko.market.entities.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {

    private List<OrderItemDto> items;
    private int price;
    private String address;

    public OrderDto(OrderEntity o){
        this.items = o.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.price = o.getPrice();
        this.address = o.getAddress();
    }
}
