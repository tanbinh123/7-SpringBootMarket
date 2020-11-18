package ru.malichenko.market.entities;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private int pricePerProduct;

    @Column(name = "price")
    private int price;

    public OrderItemEntity(ProductEntity productEntity){
        this.productEntity = productEntity;
        this.quantity = 1;
        this.price = productEntity.getPrice();
        this.pricePerProduct = productEntity.getPrice();
    }

    public void incrementQuantity(){
        quantity++;
    }

    public void decrementQuantity(){
        quantity--;
    }

}
