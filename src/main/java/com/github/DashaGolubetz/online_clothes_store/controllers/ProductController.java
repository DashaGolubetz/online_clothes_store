package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.securities.UserDetails;
import com.github.DashaGolubetz.online_clothes_store.services.CartService;
import com.github.DashaGolubetz.online_clothes_store.services.ProductService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping(value = "/add")
    public String addProduct(@NotNull Model model) {
        model.addAttribute("product", new ProductDTO());

        return "products/add";
    }

    @PostMapping(value = "/add")
    public String addProduct(@ModelAttribute(value = "product") ProductDTO productDTO) {
        productService.save(productDTO);

        return "redirect:/";
    }

    @DeleteMapping(value = "/{id}/delete")
    public String deleteProduct(@PathVariable(value = "id") int id) {
        productService.deleteById(id);

        return "redirect:/";
    }

    @GetMapping(value = "/{id}")
    public String viewProduct(@PathVariable(value = "id") int productId, Model model) {
        Optional<ProductEntity> productEntity = productService.findById(productId);

        if (productEntity.isPresent()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken) && cartService.findProductInCart(((UserDetails) authentication.getPrincipal()).getId(), productId).isEmpty())
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
