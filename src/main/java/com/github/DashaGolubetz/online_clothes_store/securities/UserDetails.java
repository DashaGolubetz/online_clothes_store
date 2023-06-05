package com.github.DashaGolubetz.online_clothes_store.securities;

import com.github.DashaGolubetz.online_clothes_store.entities.UserEntity;
import org.jetbrains.annotations.Contract;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * Класс, реализующий интерфейс {@link org.springframework.security.core.userdetails.UserDetails}, необходимый для предоставления данных о пользователе Spring'у.
 */
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    /**
     * Поле, содержащее объект класса {@link UserEntity}.
     */
    private final UserEntity userEntity;

    /**
     * Стандартный конструктор, в котором содержатся все final-поля (бины, подгружаемые Spring'ом автоматически).
     *
     * @param userEntity
     */
    @Contract(pure = true)
    public UserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * Переопределение функции {@link org.springframework.security.core.userdetails.UserDetails#getAuthorities()}.
     *
     * @return {@link Collection}<? extends {@link GrantedAuthority}> (список ролей пользователя).
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole()));
    }

    /**
     * Переопределение функции {@link org.springframework.security.core.userdetails.UserDetails#getPassword()}.
     *
     * @return {@link String} (пароль пользователя).
     */
    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    /**
     * Переопределение функции {@link org.springframework.security.core.userdetails.UserDetails#getUsername()}.
     *
     * @return {@link String} (никнейм пользователя).
     */
    @Override
    public String getUsername() {
        return userEntity.getName();
    }

    /**
     * Переопределение функции {@link org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()}.
     *
     * @return {@link Boolean} (не истёк ли срок действия аккаунта пользователя).
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Переопределение функции {@link org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()}.
     *
     * @return {@link Boolean} (не заблокирован ли аккаунт пользователя).
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Переопределение функции {@link org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()}.
     *
     * @return {@link Boolean} (не истёк ли срок действия данных пользователя).
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Переопределение функции {@link org.springframework.security.core.userdetails.UserDetails#isEnabled()}.
     *
     * @return {@link Boolean} (включён ли аккаунт пользователя).
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Функция, возвращающая идентификатор пользователя.
     *
     * @return {@link Integer} (идентификатор пользователя).
     */
    public int getId() {
        return userEntity.getId();
    }
}
