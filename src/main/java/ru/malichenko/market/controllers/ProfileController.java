package ru.malichenko.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.dto.ProfileDto;
import ru.malichenko.market.entities.Profile;
import ru.malichenko.market.entities.User;
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


    @GetMapping(produces = "application/json")
    public ProfileDto getProfile(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for user: " + principal.getName() + ". User doesn't exist"));
        return new ProfileDto(profileService.findByUserId(user.getId()));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public void editProfile(
//            @RequestBody @Validated Profile p, BindingResult bindingResult
            @RequestParam(name = "password") String password,
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "birthday") String birthday,
            @RequestParam(name = "gender") String gender,
            @RequestParam(name = "city") String city
    ) {
        Profile p = profileService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile with id: " + id + "does not exist in DB"));
        if (email != null) p.setName(name);
        if (surname != null) p.setSurname(surname);
        if (phone != null) p.setPhone(phone);
        if (email != null) p.setEmail(email);
        if (birthday != null) p.setBirthday(birthday);
        if (gender != null) p.setGender(gender);
        if (city != null) p.setCity(city);
        profileService.saveOrUpdate(p);
    }
}
