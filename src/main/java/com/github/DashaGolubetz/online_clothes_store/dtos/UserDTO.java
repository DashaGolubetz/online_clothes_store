package com.github.DashaGolubetz.online_clothes_store.dtos;

import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.entities.UserEntity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Класс, позволяющий передавать только требуемую информацию о {@link UserEntity} между клиентом и сервером (практически необходим для API).
 */
public class UserDTO {
    /**
     * Поле, передающее никнейм пользователя.
     */
    private String name;
    /**
     * Поле, передающее пароль пользователя.
     */
    private String password;
    /**
     * Поле, передающее подтверждённый пароль пользователя.
     */
    private String confirmedPassword;
    /**
     * Поле, передающее e-mail пользователя.
     */
    private String email;

    /**
     * Пустой конструктор.
     */
    @Contract(pure = true)
    public UserDTO() {

    }

    /**
     * Полный конструктор.
     *
     * @param name              никнейм пользователя.
     * @param password          пароль пользователя.
     * @param confirmedPassword подтверждённый пароль пользователя.
     * @param email             e-mail пользователя.
     */
    @Contract(pure = true)
    public UserDTO(String name, String password, String confirmedPassword, String email) {
        this.name = name;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.email = email;
    }

    /**
     * Статическая функция, позволяющая конвертировать {@link UserDTO} в {@link UserEntity}
     *
     * @param userDTO объект класса {@link UserDTO}.
     * @param role    роль пользователя.
     * @return {@link UserEntity}.
     */
    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull UserEntity convertToUserEntity(@NotNull UserDTO userDTO, String role) {
        return new UserEntity(userDTO.name, userDTO.password, userDTO.email, role);
    }

    /**
     * Get-теры и set-теры для каждого из полей.
     */

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

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
