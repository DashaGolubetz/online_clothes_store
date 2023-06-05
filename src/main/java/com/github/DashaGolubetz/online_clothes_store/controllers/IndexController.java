package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.services.ProductService;
import com.github.DashaGolubetz.online_clothes_store.services.UserService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * {@link Controller}, отвечающий за запросы к главной странице приложения по адресу "/**".
 */
@Controller
@RequestMapping(value = "")
public class IndexController {
    /**
     * Поле, содержащее объект класса {@link ProductService}.
     */
    private final ProductService productService;

    /**
     * Стандартный конструктор, в котором содержатся все final-поля (бины, подгружаемые Spring'ом автоматически).
     *
     * @param productService
     */
    @Contract(pure = true)
    public IndexController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Функция, возвращающая главную страницу (GET: "").
     *
     * @param model модель, передающая информацию во front-end.
     */
    @GetMapping(value = "")
    public String index(@NotNull Model model) {
        model.addAttribute("products", productService.findAll());

        return "index/index";
    }
}
