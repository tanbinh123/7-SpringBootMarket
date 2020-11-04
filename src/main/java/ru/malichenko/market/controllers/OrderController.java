package ru.malichenko.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.dto.CartDto;
import ru.malichenko.market.dto.OrderDto;
import ru.malichenko.market.entities.Order;
import ru.malichenko.market.entities.User;
import ru.malichenko.market.exceptions.ResourceNotFoundException;
import ru.malichenko.market.services.OrderService;
import ru.malichenko.market.services.UserService;
import ru.malichenko.market.utils.Cart;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final Cart cart;

    @GetMapping
    public List<OrderDto> getAllOrders(Principal principal) {
        return orderService.findAllUserOrdersDtosByUsername(principal.getName());
    }

    @GetMapping("/newOrder")
    public CartDto getCartDto() {
        cart.recalculate();
        return new CartDto(cart);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void confirmOrder(Principal principal,
                             @RequestParam(name = "receiverName") String receiverName,
                             @RequestParam(name = "phone") String phone,
                             @RequestParam(name = "address") String address) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order for user: " + principal.getName() + ". User doesn't exist"));
        Order order = new Order(user, cart, address, phone, receiverName);
        orderService.save(order);
        cart.clear();
        //return "redirect: /api/v1/orders"
    }
}
