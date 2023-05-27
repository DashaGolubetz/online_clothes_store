package com.github.DashaGolubetz.online_clothes_store.services;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.repositories.ProductRepository;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Contract(pure = true)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductDTO> findById(int id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        return productEntity.map(ProductEntity::convertToProductDTO);
    }
}
