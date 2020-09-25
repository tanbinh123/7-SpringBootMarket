package ru.malichenko.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "current_price")
    private int currentPrice;
}
