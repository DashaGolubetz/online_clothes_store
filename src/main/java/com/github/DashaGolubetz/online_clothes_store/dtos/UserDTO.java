package com.github.DashaGolubetz.online_clothes_store.dtos;

import com.github.DashaGolubetz.online_clothes_store.entities.UserEntity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class UserDTO {
    private String name;
    private String password;
    private String email;
    private String confirmedPassword;

    @Contract(pure = true)
    public UserDTO() {

    }

    @Contract(pure = true)
    public UserDTO(String name, String password, String confirmedPassword, String email) {
        this.name = name;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.email = email;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull UserEntity convertToUserEntity(@NotNull UserDTO userDTO, String role) {
        return new UserEntity(userDTO.name, userDTO.password, userDTO.email, role);
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
