package com.github.DashaGolubetz.online_clothes_store.repositories;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<ProductEntity, Integer> {
    @Query(value = "SELECT p.id " +
            "FROM \"product\" p " +
            "JOIN \"cart\" c ON  p.id = c.product_id " +
            "JOIN \"user\" u ON c.user_id = u.id " +
            "WHERE p.id = :product_id AND u.id = :user_id", nativeQuery = true)
    Optional<Integer> findProductIdInCart(
            @Param(value = "user_id") int userId,
            @Param(value = "product_id") int productId
    );

    @Modifying
    @Query(value = "INSERT INTO \"cart\" VALUES (:user_id, :product_id)", nativeQuery = true)
    void addToCart(@Param(value = "user_id") int userId, @Param(value = "product_id") int productId);
}
