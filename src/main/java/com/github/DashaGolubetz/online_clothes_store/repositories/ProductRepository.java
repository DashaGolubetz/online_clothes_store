package com.github.DashaGolubetz.online_clothes_store.repositories;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Класс, предоставляющий соединение с базой данных и представляющий абстракцию для отправления запросов к ней (к таблице "product").
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    /**
     * Запрос, возвращающий список всех товаров.
     *
     * @return {@link List}<{@link ProductEntity}> (список товаров).
     */
    @Override
    @NotNull
    @Query(value = "SELECT * FROM \"product\"", nativeQuery = true)
    List<ProductEntity> findAll();

    /**
     * Запрос, возвращающий товар по {productId}.
     *
     * @param productId идентификатор товара.
     * @return {@link Optional}<{@link ProductEntity}> (товар и пустое значение).
     */
    @NotNull
    @Query(value = "SELECT * FROM \"product\" WHERE id=:productId", nativeQuery = true)
    Optional<ProductEntity> findById(@Param(value = "productId") int productId);
}
