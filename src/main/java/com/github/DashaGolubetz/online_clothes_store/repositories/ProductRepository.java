package com.github.DashaGolubetz.online_clothes_store.repositories;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Override
    @NotNull
    @Query(value = "SELECT * FROM \"product\"", nativeQuery = true)
    List<ProductEntity> findAll();
}
