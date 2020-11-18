package ru.malichenko.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malichenko.market.entities.ProfileEntity;
import ru.malichenko.market.repositories.ProfileRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    public void save(ProfileEntity profileEntity) {
        profileRepository.save(profileEntity);
    }

    public Optional<ProfileEntity> findByUserId(Long id) {
        return profileRepository.findByUserEntityId(id);
    }

    public boolean existsById(Long id) {
        return profileRepository.existsById(id);
    }

    public ProfileEntity saveOrUpdate(ProfileEntity p) {
        return profileRepository.save(p);
    }

    public Optional<ProfileEntity> findById(Long id) {
        return profileRepository.findById(id);
    }

    public Optional<ProfileEntity> findByUsername(String name) {
        return profileRepository.findByUsername(name);
    }
}