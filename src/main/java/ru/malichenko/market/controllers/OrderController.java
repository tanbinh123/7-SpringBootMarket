package ru.malichenko.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.entities.Order;
import ru.malichenko.market.entities.User;
import ru.malichenko.market.services.OrderService;
import ru.malichenko.market.services.UserService;
import ru.malichenko.market.utils.Cart;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private Cart cart;

    @GetMapping
    public String showOrders(Model model, Principal principal) {
        Long id = userService.findByUsername(principal.getName()).getId();
        model.addAttribute("orders", orderService.findAllOrderByUserId(id));
        return "orders";
    }

    @GetMapping("/create")
    public String shoNewOrderPage(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "order_create";
    }

    @PostMapping("/confirm")
    @ResponseBody
    public String confirmOrder(Principal principal,
                              @RequestParam(name = "receiver_name") String receiverName,
                              @RequestParam(name = "address") String address,
                              @RequestParam(name = "phone_number") String phone) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user,cart,address,phone,receiverName);
        order = orderService.save(order);
        return "Ваш заказ № " + order.getId();
    }
}
