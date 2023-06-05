package com.github.DashaGolubetz.online_clothes_store.services;

import com.github.DashaGolubetz.online_clothes_store.controllers.CartController;
import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.repositories.CartRepository;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Класс, представляющий собой связующее звено между {@link CartController} и {@link CartRepository}, содержащее логику и обработку транспортируемых данных.
 */
@Service
@Transactional(readOnly = true)
public class CartService {
    /**
     * Поле, содержащее объект класса {@link CartRepository}.
     */
    private final CartRepository cartRepository;

    /**
     * Стандартный конструктор, в котором содержатся все final-поля (бины, подгружаемые Spring'ом автоматически).
     *
     * @param cartRepository
     */
    @Contract(pure = true)
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    /**
     * Функция, обращающаяся к функции репозитория, совершающей запрос к базе данных, возвращающий товар, находящийся в корзине у пользователя, и обрабатывающая ответ, который будет возвращён контроллеру.
     *
     * @param userId    идентификатор пользователя.
     * @param productId идентификатор товара.
     * @return {@link Optional}<{@link ProductEntity}> (товар или пустое значение).
     */
    public Optional<ProductEntity> findProductInCart(int userId, int productId) {
        return cartRepository.findProductInCart(userId, productId);
    }

    /**
     * Функция, обращающаяся к функции репозитория, совершающей запрос к базе данных, добавляющая товар в корзину пользователю.
     *
     * @param userId    идентификатор пользователя.
     * @param productId идентификатор товара.
     */
    @Transactional
    public void addProductToCart(int userId, int productId) {
        cartRepository.addProductToCart(userId, productId);
    }

    /**
     * Функция, обращающаяся к функции репозитория, совершающей запрос к базе данных, возвращающий список товаров, находящихся в корзине у пользователя, и обрабатывающая ответ, который будет возвращён контроллеру.
     *
     * @param userId идентификатор пользователя.
     * @return {@link List}<{@link ProductEntity}> (список товаров).
     */
    public List<ProductEntity> findAllProductsInCart(int userId) {
        return cartRepository.findAllProductsInCart(userId);
    }

    /**
     * Функция, обращающаяся к функции репозитория, совершающей запрос к базе данных, удаляющая товар из корзины пользователя.
     *
     * @param userId    идентификатор пользователя.
     * @param productId идентификатор товара.
     */
    @Transactional
    public void removeProductFromCart(int userId, int productId) {
        cartRepository.removeProductFromCart(userId, productId);
    }

    /**
     * Функция, обращающаяся к функции репозитория, совершающей запрос к базе данных, очищающий корзину пользователя.
     *
     * @param userId идентификатор пользователя.
     */
    @Transactional
    public void clearCart(int userId) {
        cartRepository.clearCart(userId);
    }
}
