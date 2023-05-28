package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.securities.UserDetails;
import com.github.DashaGolubetz.online_clothes_store.services.CartService;
import com.github.DashaGolubetz.online_clothes_store.services.ProductService;
import org.jetbrains.annotations.Contract;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;
    private final CartService cartService;

    @Contract(pure = true)
    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping(value = "/{id}")
    public String viewProduct(@PathVariable(value = "id") int productId, Model model) {
        Optional<ProductEntity> productEntity = productService.findById(productId);

        if (productEntity.isPresent()) {
            if (cartService.findProductInCart(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), productId).isEmpty())
                model.addAttribute("is_present", true);

            model.addAttribute("product", productEntity.get());

            return "products/product";
        }

        return "redirect:/";
    }

    @PostMapping(value = "/{id}/add")
    public String addProductToCart(@PathVariable(value = "id") int productId) {
        cartService.addProductToCart(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), productId);

        return "redirect:/";
    }
}
