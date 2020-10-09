package ru.malichenko.market.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.dto.ProductDto;
import ru.malichenko.market.entities.Product;
import ru.malichenko.market.entities.User;
import ru.malichenko.market.exceptions.ResourceNotFoundException;
import ru.malichenko.market.services.ProductService;
import ru.malichenko.market.services.UserService;
import ru.malichenko.market.utils.ProductFilter;

import javax.sql.DataSource;
import java.util.Map;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/new")
    public String showUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user_form";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        User newUser = userService.save(user);

        return "login";
    }

}
