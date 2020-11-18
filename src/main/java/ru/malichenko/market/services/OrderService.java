package ru.malichenko.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.malichenko.market.dto.OrderDto;
import ru.malichenko.market.dto.OrderItemDto;
import ru.malichenko.market.entities.OrderEntity;
import ru.malichenko.market.repositories.OrderRepository;
import ru.malichenko.market.ws.orders.Order;
import ru.malichenko.market.ws.orders.OrderItem;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private  static final Function<OrderEntity, Order> functionEntityToSoap = (orderEntity -> {
        Order order = new Order();
        order.setPrice(orderEntity.getPrice());
        order.setAddress(orderEntity.getAddress());
        order.setItems(orderEntity.getItems().stream()
                .map(OrderItemDto::new)
                .map(itemDto -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductId(itemDto.getProductId());
                    orderItem.setProductTitle(itemDto.getProductTitle());
                    orderItem.setCategoryTitle(itemDto.getCategoryTitle());
                    orderItem.setQuantity(itemDto.getQuantity());
                    orderItem.setPrice(itemDto.getPrice());
                    orderItem.setPricePerProduct(itemDto.getPricePerProduct());
                    return orderItem;
                })
                .collect(Collectors.toList()));
        return order;
    });

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    public List<OrderDto> findAllUserOrdersDtosByUsername(String username) {
        return orderRepository.findAllOrderEntityByUsername(username).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public OrderEntity save(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Transactional(readOnly = true)
    public List<Order> getAllSOAPOrdersByUsername(String username) {
        return orderRepository.findAllOrderEntityByUsername(username).stream()
                .map(functionEntityToSoap)
                .collect(Collectors.toList());
    }

//    public List<Order> getAllSOAPOrdersByUsername(String username) {
//        return orderRepository.findAllOrderEntityByUsername(username).stream()
//                .map(functionEntityToSoap)
//                .collect(Collectors.toList());
//    }
}
