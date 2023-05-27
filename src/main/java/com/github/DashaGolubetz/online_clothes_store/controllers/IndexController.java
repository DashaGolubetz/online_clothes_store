package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.services.ProductService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
public class IndexController {
    private final ProductService productService;

    @Contract(pure = true)
    public IndexController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "")
    public String index(@NotNull Model model) {
        model.addAttribute("products", productService.findAll());

        return "index/index";
    }
}
