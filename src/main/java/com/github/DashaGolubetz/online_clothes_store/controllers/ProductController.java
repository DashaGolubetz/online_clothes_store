package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.securities.UserDetails;
import com.github.DashaGolubetz.online_clothes_store.services.CartService;
import com.github.DashaGolubetz.online_clothes_store.services.ProductService;
import com.github.DashaGolubetz.online_clothes_store.services.UserService;
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

/**
 * {@link Controller}, отвечающий за запросы, связанные с товарами к приложению по адресу "/products".
 */
@Controller
@RequestMapping(value = "/products")
public class ProductController {
    /**
     * Поле, содержащее объект класса {@link ProductService}.
     */
    private final ProductService productService;
    /**
     * Поле, содержащее объект класса {@link CartService}.
     */
    private final CartService cartService;

    /**
     * Стандартный конструктор, в котором содержатся все final-поля (бины, подгружаемые Spring'ом автоматически).
     *
     * @param productService
     * @param cartService
     */
    @Contract(pure = true)
    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    /**
     * Функция, возвращающая страницу добавления товара (GET: "/products/add").
     *
     * @param model модель, передающая информацию во front-end.
     */
    @GetMapping(value = "/add")
    public String addProduct(@NotNull Model model) {
        model.addAttribute("product", new ProductDTO());

        return "products/add";
    }

    /**
     * Функция, принимающая данные с формы добавления товара и обрабатывающая их (POST: "/products/add").
     *
     * @param productDTO инфомрация о товаре, предоставленная пользователем при добавлении.
     */
    @PostMapping(value = "/add")
    public String addProduct(@ModelAttribute(value = "product") ProductDTO productDTO) {
        productService.save(productDTO);

        return "redirect:/";
    }

    /**
     * Функция, удаляющая товар из базы данных (DELETE: "/product/{productId}/delete").
     *
     * @param productId идентификатор товара.
     */
    @DeleteMapping(value = "/{productId}/delete")
    public String deleteProduct(@PathVariable(value = "productId") int productId) {
        productService.deleteById(productId);

        return "redirect:/";
    }

    /**
     * Функция, возвращающая страницу с информацией о товаре (GET: "/products/{productId}").
     *
     * @param productId идентификатор товара.
     * @param model     модель, передающая информацию во front-end.
     */
    @GetMapping(value = "/{productId}")
    public String viewProduct(@PathVariable(value = "productId") int productId, Model model) {
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

    /**
     * Функция, добавляющая товар в корзину пользователя (POST: "/products/{productId}/add").
     *
     * @param productId идентификатор товара.
     */
    @PostMapping(value = "/{productId}/add")
    public String addProductToCart(@PathVariable(value = "productId") int productId) {
        cartService.addProductToCart(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), productId);

        return "redirect:/";
    }
}
