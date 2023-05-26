package com.github.DashaGolubetz.online_clothes_store.dtos;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class ProductDTO {
    private String title;
    private String description;
    private BigDecimal price;

    public ProductDTO() {

    }

    public ProductDTO(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ProductEntity convertToProductEntity(@NotNull ProductDTO productDTO) {
        return new ProductEntity(productDTO.title, productDTO.description, productDTO.price);
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
