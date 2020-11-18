package ru.malichenko.market.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.malichenko.market.entities.ProfileEntity;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByUserEntityId(Long id);

    @Query("select p from ProfileEntity p where p.userEntity.username = ?1")
    Optional<ProfileEntity> findByUsername(String name);
}


