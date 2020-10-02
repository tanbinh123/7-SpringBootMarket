package ru.malichenko.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.malichenko.market.entities.Order;
import ru.malichenko.market.entities.OrderItem;
import ru.malichenko.market.services.OrderItemService;
import ru.malichenko.market.services.OrderService;
import ru.malichenko.market.utils.Cart;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private OrderItemService orderItemService;
    private Cart cart;

    @GetMapping
    public String showAllOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @GetMapping("/new")
    public String newOrder(Model model) {
        Order order = new Order();

        order = orderService.saveOrUpdate(order);
        for (int i = 0; i < cart.getItems().size(); i++) {
            cart.getItems().get(i).setOrder(order);
            orderItemService.saveOrUpdate(cart.getItems().get(i));
        }
        model.addAttribute("order", order);
        return "order_new";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order) {
        order.setPrice(cart.getPrice());
        orderService.saveOrUpdate(order);
        cart.clear();
        return "redirect:/orders";
    }
}
