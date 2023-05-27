package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import com.github.DashaGolubetz.online_clothes_store.services.ProductService;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;

    @Contract(pure = true)
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public String viewProduct(@PathVariable(value = "id") int id, Model model) {
        Optional<ProductDTO> productDTO = productService.findById(id);

        if (productDTO.isPresent()) {
            model.addAttribute("product", productDTO.get());

            return "products/product";
        }
        else return "redirect:/";
    }
}
