package com.github.DashaGolubetz.online_clothes_store.services;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.repositories.CartRepository;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CartService {
    private final CartRepository cartRepository;

    @Contract(pure = true)
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Optional<ProductEntity> findProductInCart(int userId, int productId) {
        return cartRepository.findProductInCart(userId, productId);
    }

    @Transactional
    public void addProductToCart(int userId, int productId) {
        cartRepository.addProductToCart(userId, productId);
    }

    public List<ProductEntity> findAllProductsInCart(int userId) {
        return cartRepository.findAllProductsInCart(userId);
    }

    @Transactional
    public void removeProductFromCart(int userId, int productId) {
        cartRepository.removeProductFromCart(userId, productId);
    }

    @Transactional
    public void clearCart(int userId) {
        cartRepository.clearCart(userId);
    }
}
