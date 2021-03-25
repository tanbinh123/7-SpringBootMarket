package ru.malichenko.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.malichenko.market.entities.OrderEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("select o from OrderEntity o where o.userEntity.username = ?1")
    List<OrderEntity> findAllOrderEntityByUsername(String username);

    @Query("select o from OrderEntity o join fetch o.items where o.userEntity.username = ?1")//данные дублируются
    List<OrderEntity> findAllOrderEntityByUsernameWithOrderItems(String username);

}