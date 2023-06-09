package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.services.ProductService;
import com.github.DashaGolubetz.online_clothes_store.services.UserDetailsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jetbrains.annotations.Contract;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * {@link RestController}, отвечающий за все возможные API-запросы к приложению по адресу "/api/**".
 */
@RestController
@RequestMapping(value = "/api")
@Tag(name = "API контроллер", description = "Основной и единственный API контроллер в приложении.")
public class APIController {
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
    public APIController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Функция, возвращающая информацию обо всех товарах (GET: "/api/products").
     *
     * @return {@link List}<{@link ProductEntity}> (список всех товаров).
     */
    @GetMapping(value = "/products")
    @ApiResponse(responseCode = "200", description = "Возвращает информацию обо всех товарах.")
    public List<ProductDTO> index() {
        return productService.findAllAndConvertToDTOS();
    }

    /**
     * Функция, возвращающая информацию о товаре (GET: "/api/products/{productId}").
     *
     * @param productId идентификатор товара.
     * @return {@link ProductEntity} (товар).
     */
    @GetMapping(value = "/products/{productId}")
    @ApiResponse(responseCode = "200", description = "Возвращает подробную информацию о конкретном товаре.")
    public ProductDTO viewProduct(@PathVariable(value = "productId") int productId) {
        ProductDTO productDTO = productService.findByIdAndConvertToDTO(productId);

        if (productDTO != null) return productDTO;

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Такой товар не найден.");
    }
}
