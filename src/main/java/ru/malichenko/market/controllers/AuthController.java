package ru.malichenko.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.malichenko.market.configs.JwtTokenUtil;
import ru.malichenko.market.dto.JwtRequest;
import ru.malichenko.market.dto.JwtResponse;
import ru.malichenko.market.entities.ProfileEntity;
import ru.malichenko.market.entities.UserEntity;
import ru.malichenko.market.exceptions.MarketError;
import ru.malichenko.market.services.ProfileService;
import ru.malichenko.market.services.RoleService;
import ru.malichenko.market.services.UserService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final RoleService roleService;
    private final UserService userService;
    private final ProfileService profileService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestParam(name = "email") String email,
                                             @RequestParam(name = "username") String username,
                                             @RequestParam(name = "password") String password) {
        if (userService.findByUsername(username).isPresent()) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Username: " + username + " already exist!"), HttpStatus.UNAUTHORIZED);
        }
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setEmail(email);
//        profileService.save(profileEntity);

        UserEntity userEntity = new UserEntity(username, encoder.encode(password), profileEntity);
        userEntity.setRoleEntities(roleService.getRole("ROLE_USER"));
        userService.saveNewUser(userEntity);
        return ResponseEntity.ok(userEntity);
    }
}
