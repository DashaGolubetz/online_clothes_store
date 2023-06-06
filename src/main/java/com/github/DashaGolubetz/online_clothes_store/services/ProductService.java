package com.github.DashaGolubetz.online_clothes_store.services;

import com.github.DashaGolubetz.online_clothes_store.controllers.CartController;
import com.github.DashaGolubetz.online_clothes_store.controllers.ProductController;
import com.github.DashaGolubetz.online_clothes_store.dtos.ProductDTO;
import com.github.DashaGolubetz.online_clothes_store.entities.ProductEntity;
import com.github.DashaGolubetz.online_clothes_store.repositories.CartRepository;
import com.github.DashaGolubetz.online_clothes_store.repositories.ProductRepository;
import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Класс, представляющий собой связующее звено между {@link ProductController} и {@link ProductRepository}, содержащее логику и обработку транспортируемых данных.
 */
@Service
@Transactional(readOnly = true)
public class ProductService {
    /**
     * Поле, содержащее объект класса {@link ProductRepository}.
     */
    private final ProductRepository productRepository;

    /**
     * Стандартный конструктор, в котором содержатся все final-поля (бины, подгружаемые Spring'ом автоматически).
     *
     * @param productRepository
     */
    @Contract(pure = true)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Функция, обращающаяся к функции репозитория, совершающей запрос к базе данных, возвращающий список всех товаров, и обрабатывающая ответ, который будет возвращён контроллеру.
     *
     * @return {@link List}<{@link ProductEntity}> (список товаров).
     */
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public List<ProductDTO> findAllAndConvertToDTOS() {
        return productRepository.findAll().stream().map(ProductEntity::convertToProductDTO).toList();
    }

    /**
     * Функция, обращающаяся к функции репозитория, совершающей запрос к базе данных, добавляющий товар.
     */
    @Transactional
    public void save(ProductDTO productDTO) {
        productRepository.save(ProductDTO.convertToProductEntity(productDTO));
    }

    /**
     * Функция, обращающаяся к функции репозитория, совершающей запрос к базе данных, удаляющий товар.
     */
    @Transactional
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    /**
     * Функция, обращающаяся к функции репозитория, совершающей запрос к базе данных, возвращающий товар, и обрабатывающая ответ, который будет возвращён контроллеру.
     *
     * @param productId идентификатор товара.
     * @return {@link Optional}<{@link ProductEntity}> (товар и пустое значение).
     */
    public Optional<ProductEntity> findById(int productId) {
        return productRepository.findById(productId);
    }

    public ProductDTO findByIdAndConvertToDTO(int productId) {
        return productRepository.findById(productId).map(ProductEntity::convertToProductDTO).orElse(null);
    }
}
