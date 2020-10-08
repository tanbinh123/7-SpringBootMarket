package ru.malichenko.market.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malichenko.market.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
