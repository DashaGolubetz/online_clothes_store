package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.securities.UserDetails;
import com.github.DashaGolubetz.online_clothes_store.services.CartService;
import com.github.DashaGolubetz.online_clothes_store.services.UserService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * {@link Controller}, отвечающий за запросы, связанные с корзиной, к приложению по адресу "/cart/**"
 */
@Controller
@RequestMapping(value = "/cart")
public class CartController {
    /**
     * Поле, содержащее объект класса {@link CartService}.
     */
    private final CartService cartService;

    /**
     * Стандартный конструктор, в котором содержатся все final-поля (бины, подгружаемые Spring'ом автоматически).
     *
     * @param cartService
     */
    @Contract(pure = true)
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Функция, возвращающая страницу с корзиной (GET: "/cart").
     *
     * @param model модель, передающая информацию во front-end.
     */
    @GetMapping(value = "")
    public String viewCart(@NotNull Model model) {
        model.addAttribute("products", cartService.findAllProductsInCart(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()));

        return "cart/cart";
    }

    /**
     * Функция, удаляющая товар из корзины (POST: "/cart/{productId}/remove").
     *
     * @param productId идентификатор продукта.
     */
    @PostMapping(value = "/{productId}/remove")
    public String removeProductFromCart(@PathVariable(value = "productId") int productId) {
        cartService.removeProductFromCart(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), productId);

        return "redirect:/cart";
    }

    /**
     * Функция, оформляющая заказ пользователя (POST: "/cart").
     *
     * @param model модель, передающая информацию во front-end.
     */
    @PostMapping(value = "")
    public String makeOrder(@NotNull Model model) {
        cartService.clearCart(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

        model.addAttribute("is_order_made", true);

        return "cart/cart";
    }
}
