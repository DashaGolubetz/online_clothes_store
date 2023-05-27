package com.github.DashaGolubetz.online_clothes_store.entities;

import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;

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
    @ManyToMany(mappedBy = "cartProductEntities")
    private List<UserEntity> cartUserEntities;

    @Contract(pure = true)
    public ProductEntity() {

    }

    @Contract(pure = true)
    public ProductEntity(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    @Contract(pure = true)
    public ProductEntity(int id, String title, String description, BigDecimal price, List<UserEntity> cartUserEntities) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.cartUserEntities = cartUserEntities;
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

    public List<UserEntity> getCartUserEntities() {
        return cartUserEntities;
    }

    public void setCartUserEntities(List<UserEntity> cartUserEntities) {
        this.cartUserEntities = cartUserEntities;
    }
}
