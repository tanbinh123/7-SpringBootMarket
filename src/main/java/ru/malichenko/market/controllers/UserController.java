package ru.malichenko.market.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.market.entities.User;
import ru.malichenko.market.services.UserService;

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
