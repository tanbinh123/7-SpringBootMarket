package ru.malichenko.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.dto.CartDto;
import ru.malichenko.market.entities.Order;
import ru.malichenko.market.entities.User;
import ru.malichenko.market.exceptions.ResourceCreationException;
import ru.malichenko.market.services.OrderService;
import ru.malichenko.market.services.UserService;
import ru.malichenko.market.utils.Cart;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private Cart cart;

    @GetMapping
    public CartDto getCartDto(){
        cart.recalculate();
        return new CartDto(cart);
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Order confirmOrder (@RequestBody @Validated Order tempOrder, Principal principal, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            throw new ResourceCreationException("Invalid order");
        }
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user,cart,tempOrder.getAddress(),tempOrder.getPhone(),tempOrder.getReceiverName());
        return orderService.save(order);
    }
}
