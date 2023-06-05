package com.github.DashaGolubetz.online_clothes_store.repositories;

import com.github.DashaGolubetz.online_clothes_store.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Класс, предоставляющий соединение с базой данных и представляющий абстракцию для отправления запросов к ней (к таблице "user").
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * Запрос, возвращающий пользователя по {username}.
     *
     * @param username никнейм пользователя.
     * @return {@link UserEntity} (пользователь).
     */
    UserEntity findByName(String username);
}
