package com.github.DashaGolubetz.online_clothes_store.services;

import com.github.DashaGolubetz.online_clothes_store.controllers.AuthController;
import com.github.DashaGolubetz.online_clothes_store.controllers.CartController;
import com.github.DashaGolubetz.online_clothes_store.dtos.UserDTO;
import com.github.DashaGolubetz.online_clothes_store.repositories.CartRepository;
import com.github.DashaGolubetz.online_clothes_store.repositories.UserRepository;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Класс, представляющий собой связующее звено между {@link AuthController} и {@link UserRepository}, содержащее логику и обработку транспортируемых данных.
 */
@Service
public class UserService {
    /**
     * Поле, содержащее объект класса {@link UserRepository}.
     */
    private final UserRepository userRepository;
    /**
     * Поле, содержащее объект класса {@link PasswordEncoder}.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Стандартный конструктор, в котором содержатся все final-поля (бины, подгружаемые Spring'ом автоматически).
     *
     * @param userRepository
     * @param passwordEncoder
     */
    @Contract(pure = true)
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Функция, обращающаяся к функции репозитория, совершающей запрос к базе данных, сохраняющий пользователя.
     *
     * @param userDTO объект класса {@link UserDTO}.
     */
    public void save(@NotNull UserDTO userDTO) {
        String password = userDTO.getPassword();

        if (userRepository.findByName(userDTO.getName()) == null && password.equals(userDTO.getConfirmedPassword())) {
            userDTO.setPassword(passwordEncoder.encode(password));
            userRepository.save(UserDTO.convertToUserEntity(userDTO, "ROLE_USER"));
        }
    }
}
