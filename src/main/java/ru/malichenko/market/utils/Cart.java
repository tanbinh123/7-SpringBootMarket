package ru.malichenko.market.utils;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.malichenko.market.entities.OrderItemEntity;
import ru.malichenko.market.entities.ProductEntity;
import ru.malichenko.market.exceptions.ResourceNotFoundException;
import ru.malichenko.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItemEntity> items;
    private int price;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void clear() {
        items.clear();
        price = 0;
    }

    public void addOrIncrement(Long productId) {
        for (OrderItemEntity o : items) {
            if (o.getProductEntity().getId().equals(productId)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        ProductEntity p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + productId + " doesn't exists (add to cart)"));
        items.add(new OrderItemEntity(p));
        recalculate();
    }

    public void decrementOrRemove(Long productId) {
        Iterator<OrderItemEntity> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemEntity o = iter.next();
            if (o.getProductEntity().getId().equals(productId)) {
                o.decrementQuantity();
                if (o.getQuantity() == 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        Iterator<OrderItemEntity> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemEntity o = iter.next();
            if (o.getProductEntity().getId().equals(productId)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        price = 0;
        for (OrderItemEntity o : items) {
            o.setPricePerProduct(o.getProductEntity().getPrice());
            o.setPrice(o.getProductEntity().getPrice() * o.getQuantity());
            price += o.getPrice();
        }
    }
}
