package com.github.DashaGolubetz.online_clothes_store.entities;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;

    public ProductEntity() {

    }

    public ProductEntity(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public ProductEntity(int id, String title, String description, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ProductDTO convertToProductDTO(@NotNull ProductEntity productEntity) {
        return new ProductDTO(productEntity.title, productEntity.description, productEntity.price);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
