package com.github.DashaGolubetz.online_clothes_store.services;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (ProductEntity productEntity : productRepository.findAll())
            productDTOS.add(ProductEntity.convertToProductDTO(productEntity));

        return productDTOS;
    }
}
