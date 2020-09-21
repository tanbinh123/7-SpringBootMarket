package ru.malichenko.market.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malichenko.market.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
