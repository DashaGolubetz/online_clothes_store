package com.github.DashaGolubetz.online_clothes_store.services;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.repositories.CartRepository;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CartService {
    private final CartRepository cartRepository;

    @Contract(pure = true)
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Optional<Integer> findProductInCart(int userId, int productId) {
        return cartRepository.findProductIdInCart(userId, productId);
    }

    @Transactional
    public void addToCart(int userId, int productId) {
        cartRepository.addToCart(userId, productId);
    }
}
