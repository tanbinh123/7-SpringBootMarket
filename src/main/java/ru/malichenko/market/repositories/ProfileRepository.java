package ru.malichenko.market.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.malichenko.market.entities.Profile;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserId(Long id);

    @Query("select p from Profile p where p.user.username = ?1")
    Optional<Profile> findByUsername(String name);
}


