package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.securities.UserDetails;
import com.github.DashaGolubetz.online_clothes_store.services.CartService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    private final CartService cartService;

    @Contract(pure = true)
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "")
    public String viewCart(@NotNull Model model) {
        model.addAttribute("products", cartService.findAllProductsInCart(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));

        return "cart/cart";
    }

    @PostMapping(value = "/{id}/remove")
    public String removeProductFromCart(@PathVariable(value = "id") int productId) {
        cartService.removeProductFromCart(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), productId);

        return "redirect:/cart";
    }

    @PostMapping(value = "")
    public String makeOrder(@NotNull Model model) {
        cartService.clearCart(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

        model.addAttribute("is_order_made", true);

        return "cart/cart";
    }
}
