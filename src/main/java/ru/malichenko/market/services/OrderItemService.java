package ru.malichenko.market.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malichenko.market.entities.Order;
import ru.malichenko.market.entities.OrderItem;
import ru.malichenko.market.repositories.OrderItemRepository;
import ru.malichenko.market.repositories.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    public OrderItem saveOrUpdate(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
