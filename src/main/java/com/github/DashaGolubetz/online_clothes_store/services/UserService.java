package com.github.DashaGolubetz.online_clothes_store.services;

import com.github.DashaGolubetz.online_clothes_store.dtos.UserDTO;
import com.github.DashaGolubetz.online_clothes_store.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(@NotNull UserDTO userDTO) {
        String password = userDTO.getPassword();

        if (userRepository.findByName(userDTO.getName()) == null && password.equals(userDTO.getConfirmedPassword())) {
            userDTO.setPassword(passwordEncoder.encode(password));
            userRepository.save(UserDTO.convertToUserEntity(userDTO, "ROLE_USER"));
        }
    }
}
