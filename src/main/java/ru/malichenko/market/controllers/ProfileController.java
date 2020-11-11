package ru.malichenko.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.dto.ProfileDto;
import ru.malichenko.market.entities.Profile;
import ru.malichenko.market.entities.User;
import ru.malichenko.market.exceptions.MarketError;
import ru.malichenko.market.exceptions.ResourceNotFoundException;
import ru.malichenko.market.services.ProfileService;
import ru.malichenko.market.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping(produces = "application/json")
    public ProfileDto getCurrentProfile(Principal principal) {
//        return profileService.findByUsername(principal.getName()).stream().map(ProfileDto::new).findAny();
        Profile p = profileService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for current user"));
        return new ProfileDto(p);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> editProfile(Principal principal, @RequestBody ProfileDto profileDto) {
        User currentUser = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find current user"));
        if (profileDto.getConfirmationPassword() == null || !passwordEncoder.matches(profileDto.getConfirmationPassword(), currentUser.getPassword())) {
            new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Incorrect password"), HttpStatus.BAD_REQUEST);
        }
        Profile p = profileService.findById(profileDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for current user"));
        p.setName(profileDto.getName());
        p.setSurname(profileDto.getSurname());
        p.setPhone(profileDto.getPhone());
        p.setEmail(profileDto.getEmail());
        p.setBirthday(profileDto.getBirthday());
        p.setGender(profileDto.getGender());
        p.setCity(profileDto.getCity());
        profileService.saveOrUpdate(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
