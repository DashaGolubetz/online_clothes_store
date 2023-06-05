package com.github.DashaGolubetz.online_clothes_store.services;

import com.github.DashaGolubetz.online_clothes_store.entities.UserEntity;
import com.github.DashaGolubetz.online_clothes_store.repositories.UserRepository;
import org.jetbrains.annotations.Contract;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Contract(pure = true)
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByName(username);

        if (userEntity == null) throw new UsernameNotFoundException("Пользователь с таким именем не найден.");

        return new com.github.DashaGolubetz.online_clothes_store.securities.UserDetails(userEntity);
    }
}
