package ru.malichenko.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malichenko.market.entities.Order;
import ru.malichenko.market.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public List<Order> findAllOrderByUserId(Long id){
        return orderRepository.findAllOrderByUserId(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
