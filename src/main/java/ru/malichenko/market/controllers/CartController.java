package ru.malichenko.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malichenko.market.dto.CartDto;
import ru.malichenko.market.utils.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;

    @GetMapping
    public CartDto getCartDto(){
        cart.recalculate();
        return new CartDto(cart);
    }

    @GetMapping("/add/{product_id}")
    public void addToCart(@PathVariable(name = "product_id") Long productId) {
        cart.addOrIncrement(productId);
    }

    @GetMapping("/dec/{product_id}")
    public void decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId){
        cart.decrementOrRemove(productId);
    }

    @GetMapping("/remove/{product_id}")
    public void removeProduct(
            @PathVariable(name = "product_id") Long productId){
        cart.remove(productId);
    }

}
