package com.github.DashaGolubetz.online_clothes_store.entities;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;

/**
 * Сущность (модель) товара - класс, репрезентующий таблицу "product" базы данных.
 */
@Entity
@Table(name = "product")
public class ProductEntity {
    /**
     * Поле, отражающее колонку "id", содержащее значение идентификатора товара.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    /**
     * Поле, отражающее колонку "title", содержащее значение названия товара.
     */
    @Column(name = "title")
    private String title;
    /**
     * Поле, отражающее колонку "description", содержащее значение описания товара.
     */
    @Column(name = "description")
    private String description;
    /**
     * Поле, отражающее колонку "price", содержащее значение цены товара.
     */
    @Column(name = "price")
    private BigDecimal price;
    /**
     * Поле, отражающее отношение "многие ко многим" между таблицами "product" и "user" при помощи связующей таблицы "cart", содержащее список пользователей, у которых этот товар присутствует в корзине.
     */
    @ManyToMany(mappedBy = "cartProductEntities")
    private List<UserEntity> cartUserEntities;

    /**
     * Пустой конструктор.
     */
    @Contract(pure = true)
    public ProductEntity() {

    }

    /**
     * Частичный конструктор, необходимый для конвертиции {@link ProductDTO} в {@link ProductEntity}.
     *
     * @param title       название товара.
     * @param description описание товара.
     * @param price       цена товара.
     */
    @Contract(pure = true)
    public ProductEntity(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    /**
     * Полный конструктор.
     *
     * @param id               идентификатор товара.
     * @param title            название товара.
     * @param description      описание товара.
     * @param price            цена товара.
     * @param cartUserEntities список пользователей, у которых этот товар присутствует в корзине.
     */
    @Contract(pure = true)
    public ProductEntity(int id, String title, String description, BigDecimal price, List<UserEntity> cartUserEntities) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.cartUserEntities = cartUserEntities;
    }

    /**
     * Статическая функция, необходимая для конвертации {@link ProductEntity} в {@link ProductDTO}.
     *
     * @param productEntity объект класса {@link ProductEntity}.
     * @return {@link ProductDTO}.
     */
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ProductDTO convertToProductDTO(@NotNull ProductEntity productEntity) {
        return new ProductDTO(productEntity.title, productEntity.description, productEntity.price);
    }

    /**
     * Get-теры и set-теры для каждого из полей класса.
     */

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

    public List<UserEntity> getCartUserEntities() {
        return cartUserEntities;
    }

    public void setCartUserEntities(List<UserEntity> cartUserEntities) {
        this.cartUserEntities = cartUserEntities;
    }
}
