package com.github.DashaGolubetz.online_clothes_store.dtos;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * Класс, позволяющий передавать только требуемую информацию о {@link ProductEntity} между клиентом и сервером (практически необходим для API).
 */
public class ProductDTO {
    /**
     * Поле, передающее название товара.
     */
    private String title;
    /**
     * Поле, передающее описание товара.
     */
    private String description;
    /**
     * Поле, передающее цену товара.
     */
    private BigDecimal price;

    /**
     * Пустой конструктор.
     */
    @Contract(pure = true)
    public ProductDTO() {

    }

    /**
     * Полный конструктор. Необходим для конвертации {@link ProductEntity} в {@link ProductDTO}.
     *
     * @param title       название товара.
     * @param description описание товара.
     * @param price       цена товара.
     */
    @Contract(pure = true)
    public ProductDTO(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    /**
     * Статическая функция, конвертирующая {@link ProductDTO} в {@link ProductEntity}.
     *
     * @param productDTO объект класса {@link ProductDTO}.
     * @return {@link ProductEntity}.
     */
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ProductEntity convertToProductEntity(@NotNull ProductDTO productDTO) {
        return new ProductEntity(productDTO.title, productDTO.description, productDTO.price);
    }

    /**
     * Get-теры и set-теры для каждого из полей класса.
     */

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
