package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.services.ProductService;
import org.jetbrains.annotations.Contract;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class APIController {
    private final ProductService productService;

    @Contract(pure = true)
    public APIController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    public List<ProductEntity> index() {
        return productService.findAll();
    }

    @GetMapping(value = "/products/{id}")
    public ProductEntity viewProduct(@PathVariable(value = "id") int id) {
        Optional<ProductEntity> productEntity = productService.findById(id);

        if (productEntity.isPresent()) return productEntity.get();

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Такой товар не найден.");
    }
}
