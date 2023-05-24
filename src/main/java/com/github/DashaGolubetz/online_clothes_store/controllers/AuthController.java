package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.dtos.UserDTO;
import com.github.DashaGolubetz.online_clothes_store.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public String registerPage(@NotNull Model model) {
        model.addAttribute("user", new UserDTO());

        return "auth/register";
    }

    @PostMapping(value = "/register")
    public String registerProcess(@ModelAttribute(value = "user") UserDTO userDTO) {
        userService.save(userDTO);

        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "auth/login";
    }
}
