package ru.malichenko.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import ru.malichenko.market.utils.Cart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "receiver_name")
    private String receiverName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "orderEntity")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItemEntity> items;

    @Column(name = "price")
    private int price;

    public OrderEntity(UserEntity userEntity, Cart cart, String address, String phone, String receiverName){
        this.userEntity = userEntity;
        this.receiverName = receiverName;
        this.price = cart.getPrice();
        this.address = address;
        this.phone = phone;
        this.items = new ArrayList<>();
        cart.getItems().forEach(orderItemEntity -> {
            orderItemEntity.setOrderEntity(this);
            items.add(orderItemEntity);
        });
    }
}