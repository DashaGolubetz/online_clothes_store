package com.github.DashaGolubetz.online_clothes_store.repositories;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Класс, предоставляющий соединение с базой данных и представляющий абстракцию для отправления запросов к ней (к таблице "cart").
 */
@Repository
public interface CartRepository extends JpaRepository<ProductEntity, Integer> {
    /**
     * Запрос, возвращающий товар по {productId} в корзине у пользователя по {userId}.
     *
     * @param userId    идентификатор пользователя.
     * @param productId идентификатор товара.
     * @return {@link Optional}<{@link ProductEntity}> (товар или пустое значение).
     */
    @Query(value = "SELECT p.id, p.title, p.description, p.price " +
            "FROM \"product\" p " +
            "JOIN \"cart\" c ON  p.id = c.product_id " +
            "JOIN \"user\" u ON c.user_id = u.id " +
            "WHERE p.id = :product_id AND u.id = :user_id", nativeQuery = true)
    Optional<ProductEntity> findProductInCart(
            @Param(value = "user_id") int userId,
            @Param(value = "product_id") int productId
    );

    /**
     * Запрос, добавляющий товар по {productId} в корзину к пользователю по {userId}.
     *
     * @param userId    идентификатор пользователя.
     * @param productId идентификатор товара.
     */
    @Modifying
    @Query(value = "INSERT INTO \"cart\" VALUES (:user_id, :product_id)", nativeQuery = true)
    void addProductToCart(@Param(value = "user_id") int userId, @Param(value = "product_id") int productId);

    /**
     * Запрос, возвращающий список всех товаров в корине у пользователя по {userId}.
     *
     * @param userId идентификатор пользователя.
     * @return {@link List}<{@link ProductEntity}> (список товаров).
     */
    @Query(value = "SELECT p.id, p.title, p.description, p.price " +
            "FROM \"product\" p " +
            "JOIN \"cart\" c ON c.product_id = p.id " +
            "JOIN \"user\" u ON u.id = c.user_id " +
            "WHERE u.id = :user_id", nativeQuery = true)
    List<ProductEntity> findAllProductsInCart(@Param(value = "user_id") int userId);

    /**
     * Запрос, удаляющий товар по {productId} из корзины пользователя по {userId}.
     *
     * @param userId    идентификатор пользователя.
     * @param productId идентификатор товара.
     */
    @Modifying
    @Query(value = "DELETE FROM \"cart\" WHERE user_id = :user_id AND product_id = :product_id", nativeQuery = true)
    void removeProductFromCart(@Param(value = "user_id") int userId, @Param(value = "product_id") int productId);

    /**
     * Запрос, очищающий коризну пользователя по {userId}.
     *
     * @param userId идентификатор пользователя.
     */
    @Modifying
    @Query(value = "DELETE FROM \"cart\" WHERE user_id = :user_id", nativeQuery = true)
    void clearCart(@Param(value = "user_id") int userId);
}
