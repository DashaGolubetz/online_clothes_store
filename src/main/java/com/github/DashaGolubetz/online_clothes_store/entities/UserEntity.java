package com.github.DashaGolubetz.online_clothes_store.entities;

import com.github.DashaGolubetz.online_clothes_store.dtos.UserDTO;
import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;

import java.util.List;

/**
 * Сущность (модель) пользователя - класс, репрезентующий таблицу "user" базы данных.
 */
@Entity
@Table(name = "\"user\"")
public class UserEntity {
    /**
     * Поле, отражающее колонку "id", содержащее значение идентификатора пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * Поле, отражающее колонку "name", содержащее значение никнейма пользователя.
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * Поле, отражающее колонку "password", содержащее значение пароля пользователя.
     */
    @Column(name = "password", unique = true, nullable = false)
    private String password;
    /**
     * Поле, отражающее колонку "email", содержащее значение e-mail'а пользователя.
     */
    @Column(name = "email", nullable = false)
    private String email;
    /**
     * Поле, отражающее колонку "role", содержащее значение роли пользователя.
     */
    @Column(name = "role", nullable = false)
    private String role;
    /**
     * Поле, отражающее отношение "многие ко многим" между таблицами "user" и "product" при помощи связующей таблицы "cart", содержащее список продуктов, которые присутствуют у данного пользователя в корзине.
     */
    @ManyToMany
    @JoinTable(
            name = "cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> cartProductEntities;

    /**
     * Пустой конструктор.
     */
    @Contract(pure = true)
    public UserEntity() {

    }

    /**
     * Частичный конструктор, необходимый для конвертации {@link UserEntity} в {@link UserDTO}.
     *
     * @param name     никнейм пользователя.
     * @param password пароль пользователя.
     * @param email    e-mail пользователя.
     * @param role     роль пользователя.
     */
    @Contract(pure = true)
    public UserEntity(String name, String password, String email, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    /**
     * Полный конструктор.
     *
     * @param id                  идентификатор пользователя.
     * @param name                никнейм пользователя.
     * @param password            пароль пользователя.
     * @param email               e-mail пользователя.
     * @param role                роль пользователя.
     * @param cartProductEntities список продуктов, которые присутствуют у данного пользователя в корзине.
     */
    @Contract(pure = true)
    public UserEntity(int id, String name, String password, String email, String role, List<ProductEntity> cartProductEntities) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.cartProductEntities = cartProductEntities;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
