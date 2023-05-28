package com.github.DashaGolubetz.online_clothes_store.repositories;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<ProductEntity, Integer> {
    @Query(value = "SELECT p.id, p.title, p.description, p.price " +
            "FROM \"product\" p " +
            "JOIN \"cart\" c ON  p.id = c.product_id " +
            "JOIN \"user\" u ON c.user_id = u.id " +
            "WHERE p.id = :product_id AND u.id = :user_id", nativeQuery = true)
    Optional<ProductEntity> findProductInCart(
            @Param(value = "user_id") int userId,
            @Param(value = "product_id") int productId
    );

    @Modifying
    @Query(value = "INSERT INTO \"cart\" VALUES (:user_id, :product_id)", nativeQuery = true)
    void addProductToCart(@Param(value = "user_id") int userId, @Param(value = "product_id") int productId);

    @Query(value = "SELECT p.id, p.title, p.description, p.price " +
            "FROM \"product\" p " +
            "JOIN \"cart\" c ON c.product_id = p.id " +
            "JOIN \"user\" u ON u.id = c.user_id " +
            "WHERE u.id = :user_id", nativeQuery = true)
    List<ProductEntity> findAllProductsInCart(@Param(value = "user_id") int userId);

    @Modifying
    @Query(value = "DELETE FROM \"cart\" WHERE user_id = :user_id AND product_id = :product_id", nativeQuery = true)
    void removeProductFromCart(@Param(value = "user_id") int userId, @Param(value = "product_id") int productId);

    @Modifying
    @Query(value = "DELETE FROM \"cart\" WHERE user_id = :user_id", nativeQuery = true)
    void clearCart(@Param(value = "user_id") int userId);
}
