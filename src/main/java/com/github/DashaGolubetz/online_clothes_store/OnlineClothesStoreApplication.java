package com.github.DashaGolubetz.online_clothes_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс, откуда происходит запуск приложения.
 */
@SpringBootApplication
public class OnlineClothesStoreApplication {
    /**
     * Функция, запускающая приложение.
     *
     * @param args аргументы.
     */
    public static void main(String[] args) {
        SpringApplication.run(OnlineClothesStoreApplication.class, args);
    }

}
