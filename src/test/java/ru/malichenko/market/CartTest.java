package ru.malichenko.market;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.malichenko.market.entities.CategoryEntity;
import ru.malichenko.market.entities.ProductEntity;
import ru.malichenko.market.services.ProductService;
import ru.malichenko.market.utils.Cart;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CartTest {

    @Autowired
    private Cart cart;

    @MockBean
    private ProductService productService;

    @Test
    public void cartTest(){
        for (int i = 0; i < 10; i++) {
            ProductEntity product = new ProductEntity();
            product.setId(i+1L);
            product.setPrice(10000%(i+1));
            product.setCategoryEntity(new CategoryEntity());
            product.setTitle("Product"+i);
            Mockito.doReturn(Optional.of(product)).when(productService).findById(product.getId());
            cart.addOrIncrement(product.getId());
        }
        assertEquals(10,cart.getItems().size());
        cart.remove(2L);
        assertEquals(9, cart.getItems().size());
        cart.addOrIncrement(1L);
        assertEquals(9, cart.getItems().size());
        cart.decrementOrRemove(3L);
        assertEquals(8, cart.getItems().size());
        cart.clear();
        cart.recalculate();
        assertEquals(0,cart.getItems().size());
    }

    @Test
    public void cartNotNullTest(){
        assertNotNull(cart);
        assertNotNull(cart.getItems());
    }
}
