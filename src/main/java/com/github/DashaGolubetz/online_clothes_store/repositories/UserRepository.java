package com.github.DashaGolubetz.online_clothes_store.repositories;

import com.github.DashaGolubetz.online_clothes_store.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByName(String username);
}
