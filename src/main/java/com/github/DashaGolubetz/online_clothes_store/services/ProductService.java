package com.github.DashaGolubetz.online_clothes_store.services;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.repositories.ProductRepository;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Contract(pure = true)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public void save(ProductDTO productDTO) {
        productRepository.save(ProductDTO.convertToProductEntity(productDTO));
    }

    @Transactional
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public Optional<ProductEntity> findById(int id) {
        return productRepository.findById(id);
    }
}
