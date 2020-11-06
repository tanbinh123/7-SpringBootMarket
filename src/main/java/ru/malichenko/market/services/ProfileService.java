package ru.malichenko.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malichenko.market.entities.Profile;
import ru.malichenko.market.repositories.ProfileRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    public Profile findByUserId(Long id) {
        return profileRepository.findByUserId(id);
    }

    public boolean existsById(Long id) {
        return profileRepository.existsById(id);
    }

    public void saveOrUpdate(Profile p) {
        profileRepository.save(p);
    }

    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }
}