package com.github.DashaGolubetz.online_clothes_store.controllers;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import com.github.DashaGolubetz.online_clothes_store.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class APIController {
    private final ProductService productService;

    public APIController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products")
    public List<ProductDTO> index() {
        return productService.findAll();
    }
}
