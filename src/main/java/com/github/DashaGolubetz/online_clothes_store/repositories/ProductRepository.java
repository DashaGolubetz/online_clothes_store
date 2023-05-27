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

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Override
    @NotNull
    @Query(value = "SELECT * FROM \"product\"", nativeQuery = true)
    List<ProductEntity> findAll();

    @NotNull
    @Query(value = "SELECT * FROM \"product\" WHERE id=:id", nativeQuery = true)
    Optional<ProductEntity> findById(@Param(value = "id") int id);
}
