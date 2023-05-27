package com.github.DashaGolubetz.online_clothes_store.entities;

import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;

import java.util.List;

@Entity
@Table(name = "\"user\"")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
    @ManyToMany
    @JoinTable(
            name = "cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> cartProductEntities;

    @Contract(pure = true)
    public UserEntity() {

    }

    @Contract(pure = true)
    public UserEntity(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Contract(pure = true)
    public UserEntity(int id, String name, String password, String role, List<ProductEntity> cartProductEntities) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.cartProductEntities = cartProductEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<ProductEntity> getCartProductEntities() {
        return cartProductEntities;
    }

    public void setCartProductEntities(List<ProductEntity> cartProductEntities) {
        this.cartProductEntities = cartProductEntities;
    }
}
