package com.github.DashaGolubetz.online_clothes_store.services;

import com.github.DashaGolubetz.online_clothes_store.controllers.AuthController;
import com.github.DashaGolubetz.online_clothes_store.controllers.CartController;
import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.entities.UserEntity;
import com.github.DashaGolubetz.online_clothes_store.repositories.CartRepository;
import com.github.DashaGolubetz.online_clothes_store.repositories.UserRepository;
import org.jetbrains.annotations.Contract;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Класс, представляющий собой связующее звено между {@link AuthController} и {@link UserRepository}, содержащее логику и обработку транспортируемых данных.
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    /**
     * Поле, содержащее объект класса {@link UserRepository}.
     */
    private final UserRepository userRepository;

    /**
     * Стандартный конструктор, в котором содержатся все final-поля (бины, подгружаемые Spring'ом автоматически).
     *
     * @param userRepository
     */
    @Contract(pure = true)
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Функция, обращающаяся к функции репозитория, совершающей запрос к базе данных, возвращающий пользователя, и обрабатывающая ответ, который будет возвращён контроллеру.
     *
     * @param username никнейм пользователя.
     * @return {@link UserEntity} (пользователь).
     * @throws UsernameNotFoundException выбрасывается, если нет пользователя с таким никнеймом.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByName(username);

        if (userEntity == null) throw new UsernameNotFoundException("Пользователь с таким именем не найден.");

        return new com.github.DashaGolubetz.online_clothes_store.securities.UserDetails(userEntity);
    }
}
