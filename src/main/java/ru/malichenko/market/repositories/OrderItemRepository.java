package ru.malichenko.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malichenko.market.entities.Order;
import ru.malichenko.market.entities.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

//    @Query("select o from Order o where o.customer.id = ?1")
//    List<Order> findAllByCustomerId(Long customerId);
}
