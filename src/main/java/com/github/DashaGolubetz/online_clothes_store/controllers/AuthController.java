package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.dtos.UserDTO;
import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.services.ProductService;
import com.github.DashaGolubetz.online_clothes_store.services.UserService;
import com.github.DashaGolubetz.online_clothes_store.validators.UserDTOValidator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * {@link Controller}, отвечающий за авторизационные запросы к приложению по адресу "/**"
 */
@Controller
@RequestMapping(value = "")
public class AuthController {
    /**
     * Поле, содержащее объект класса {@link UserService}.
     */
    private final UserService userService;
    /**
     * Поле, содержащее объект класса {@link UserDTOValidator}.
     */
    private final UserDTOValidator userDTOValidator;

    /**
     * Стандартный конструктор, в котором содержатся все final-поля (бины, подгружаемые Spring'ом автоматически).
     *
     * @param userService
     * @param userDTOValidator
     */
    @Contract(pure = true)
    public AuthController(UserService userService, UserDTOValidator userDTOValidator) {
        this.userService = userService;
        this.userDTOValidator = userDTOValidator;
    }

    /**
     * Функция, возвращающая страницу регистрации (GET: "/register").
     *
     * @param model модель, передающая информацию во front-end.
     */
    @GetMapping(value = "/register")
    public String registerPage(@NotNull Model model) {
        model.addAttribute("user", new UserDTO());

        return "auth/register";
    }

    /**
     * Функция, принимающая данные со страницы регистрации (POST: "/register").
     *
     * @param userDTO       информация, предоставленная пользователем при регистрации.
     * @param bindingResult объект класса {@link BindingResult}, куда будут складываться все возможные ошибки, допущенные пользователем при регистрации.
     */
    @PostMapping(value = "/register")
    public String registerProcess(@ModelAttribute(value = "user") UserDTO userDTO, BindingResult bindingResult) {
        userDTOValidator.validate(userDTO, bindingResult);

        if (bindingResult.hasErrors()) return "auth/register";

        userService.save(userDTO);

        return "redirect:/login";
    }

    /**
     * Функция, возвращающая страницу входа (GET: "/login").
     */
    @GetMapping(value = "/login")
    public String loginPage() {
        return "auth/login";
    }
}
